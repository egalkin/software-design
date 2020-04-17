package ru.ifmo.galkin.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import ru.ifmo.galkin.search.SearchEngine;
import ru.ifmo.galkin.search.SearchServer;
import ru.ifmo.galkin.search.SearchServerStub;
import ru.ifmo.galkin.search.response.SearchResult;
import scala.concurrent.duration.FiniteDuration;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MasterRequestActor extends AbstractActor {
    private static final Set<SearchEngine> ENGINES = EnumSet.allOf(SearchEngine.class);
    private static final int TIMOUT_PERIOD = 15;
    private int childId;

    private ActorRef requestSender;


    private List<SearchResult> responses;
    private final HashMap<SearchEngine, SearchServer> searchServers;

    public  MasterRequestActor() {
        this.childId = 0;
        this.responses = new ArrayList<>();
        this.searchServers = new HashMap<>();
        ENGINES.forEach(searchEngine -> searchServers.put(searchEngine, new SearchServerStub(searchEngine)));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, request -> {
                    this.requestSender = getSender();

                    ENGINES.forEach(engine -> getContext()
                        .actorOf(
                                Props.create(ChildRequestActor.class, searchServers.get(engine)),
                                String.format("child%d", childId++)

                        ).tell(request, self())
                    );

                    context().system().scheduler()
                            .scheduleOnce(
                                    FiniteDuration.apply(TIMOUT_PERIOD, TimeUnit.SECONDS),
                                    self(),
                                    ServiceMessage.TIMEOUT_MSG,
                                    context().system().dispatcher(),
                                    ActorRef.noSender()
                            );
                }).match(SearchResult.class, response -> {
                    responses.add(response);

                    if (responses.size() == ENGINES.size()) {
                        sendResult();
                    }
                }).match(ServiceMessage.class, serviceMessage -> {
                    switch (serviceMessage) {
                        case TIMEOUT_MSG:
                            sendResult();
                            break;
                        case STOP_MSG:
                            getContext().stop(this.self());
                            break;
                    }
                }).build();
    }


    private void sendResult() {
        requestSender.tell(responses, self());
        self().tell(ServiceMessage.STOP_MSG, self());
    }


    private  enum ServiceMessage {
        TIMEOUT_MSG, STOP_MSG
    }
}

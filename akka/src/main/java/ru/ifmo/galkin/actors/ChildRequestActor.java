package ru.ifmo.galkin.actors;

import akka.actor.AbstractActor;

import ru.ifmo.galkin.search.SearchServer;

public class ChildRequestActor extends AbstractActor {

    private final SearchServer searchServer;

    public ChildRequestActor(SearchServer searchServer) {
        this.searchServer = searchServer;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class,
                        msg -> getSender().tell(searchServer.search(msg), getSender())
                ).build();
    }
}

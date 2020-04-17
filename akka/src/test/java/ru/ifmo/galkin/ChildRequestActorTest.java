package ru.ifmo.galkin;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.ifmo.galkin.actors.ChildRequestActor;
import ru.ifmo.galkin.search.SearchEngine;
import ru.ifmo.galkin.search.SearchServerStub;
import ru.ifmo.galkin.search.response.SearchResult;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

public class ChildRequestActorTest {
    private static ActorSystem system;

    @BeforeAll
    public static void setup() {
        system = ActorSystem.create();
    }

    @AfterAll
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testChildRequestActor() {
        new TestKit(system) {
            {
                final Props props = Props.create(ChildRequestActor.class, new SearchServerStub(SearchEngine.GOOGLE));
                final ActorRef master = system.actorOf(props);

                within(FiniteDuration.apply(1, TimeUnit.SECONDS),
                        () -> {
                            master.tell("hello", getRef());
                            expectMsgAnyClassOf(SearchResult.class);
                            return null;
                        });
            }
        };
    }
}

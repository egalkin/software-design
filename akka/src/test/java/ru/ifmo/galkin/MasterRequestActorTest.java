package ru.ifmo.galkin;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.ifmo.galkin.actors.MasterRequestActor;
import scala.concurrent.duration.FiniteDuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MasterRequestActorTest {
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
    public void testMasterRequestActor() {
        new TestKit(system) {
            {
                final Props props = Props.create(MasterRequestActor.class);
                final ActorRef master = system.actorOf(props);

                within(FiniteDuration.apply(1, TimeUnit.SECONDS),
                        () -> {
                            master.tell("hello", getRef());
                            expectMsgAnyClassOf(List.class);
                            return null;
                        });
            }
        };
    }
}

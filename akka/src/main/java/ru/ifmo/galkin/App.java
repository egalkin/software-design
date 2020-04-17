package ru.ifmo.galkin;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import ru.ifmo.galkin.actors.MasterRequestActor;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


import static akka.pattern.PatternsCS.ask;

public class App {

    private static final String EXIT = "exit";


    public static void main(String... args) {
        ActorSystem searchSystem = ActorSystem.create("SearchSystem");
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Enter search request:");
            String request = in.nextLine();

            if (request.equals(EXIT))
                break;

            ActorRef parent = searchSystem.actorOf(Props.create(MasterRequestActor.class), "master");

            Object response = ask(parent, request, Timeout.apply(15, TimeUnit.SECONDS))
                    .toCompletableFuture()
                    .join();

            System.out.println(response);

        }
        searchSystem.terminate();

    }

}

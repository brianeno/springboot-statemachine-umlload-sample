package com.brianeno.statemachine;

import com.brianeno.statemachine.config.VehicleUmlStateMachineConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableStateMachine
@RequiredArgsConstructor
public class SpringUmlStateMachineApplication implements CommandLineRunner {

    private final StateMachineFactory<String, String> stateMachineFactory;

    public static void main(String[] args) {
        SpringApplication.run(SpringUmlStateMachineApplication.class, args);
    }

    @Override
    public void run(String... args) {

        StateMachine<String, String> stateMachine = stateMachineFactory.getStateMachine(VehicleUmlStateMachineConfig.MACHINE_ID);

        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("Begin_SSM")
            .build())).subscribe();
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("Sell Vehicle")
              .build())).subscribe();
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("StartAssembly")
            .build())).subscribe();
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("AssemblyComplete")
            .build())).subscribe();
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("SHIP")
            .build())).subscribe();
    }
}
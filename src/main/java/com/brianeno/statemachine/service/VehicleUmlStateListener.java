package com.brianeno.statemachine.service;

import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.Optional;


public class VehicleUmlStateListener extends StateMachineListenerAdapter<String, String> {
    @Override
    public void stateChanged(State<String, String> from, State<String, String> to) {
        System.out.println("state changed from " + offNullableState(from) + " to " + offNullableState(to));
    }

    private Object offNullableState(State<String, String> s) {
        return Optional.ofNullable(s).map(State::getId).orElse(null);
    }
}

package com.brianeno.statemachine.config;

import com.brianeno.statemachine.service.VehicleUmlStateListener;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.model.StateMachineModelFactory;
import org.springframework.statemachine.uml.UmlStateMachineModelFactory;

@Configuration
@EnableStateMachineFactory
@AllArgsConstructor
public class VehicleUmlStateMachineConfig extends StateMachineConfigurerAdapter<String, String> {

    public static final String MACHINE_ID = "umlMachineId";

    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
        config.withConfiguration()
            .machineId(MACHINE_ID)
            .autoStartup(true)
            .listener(new VehicleUmlStateListener());
    }

    @Override
    public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
        model
            .withModel()
            .factory(modelFactory());
    }

    @Bean
    public StateMachineModelFactory<String, String> modelFactory() {
        return new UmlStateMachineModelFactory("classpath:VehicleState.uml");
    }
}

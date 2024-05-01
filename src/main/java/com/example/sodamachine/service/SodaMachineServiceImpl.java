package com.example.sodamachine.service;

import com.example.sodamachine.model.SodaMachine;
import com.example.sodamachine.model.State;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SodaMachineServiceImpl implements SodaMachineService {
    private final SodaMachine sodaMachine;

    public SodaMachineServiceImpl() {
        Map<String, Integer> initialSodaCounts = new HashMap<>();
        initialSodaCounts.put("Coke", 10);
        initialSodaCounts.put("Pepsi", 5);
        this.sodaMachine = new SodaMachine(initialSodaCounts);
    }

    @Override
    public String insertQuarter() {
       return sodaMachine.insertQuarter();
    }

    @Override
    public String removeQuarter() {
        return sodaMachine.removeQuarter();
    }

    @Override
    public String pushButton(String sodaName) {
        return sodaMachine.pushButton(sodaName);
    }

    @Override
    public State getState() {
        return sodaMachine.getState();
    }

    @Override
    public Map<String, Integer> getSodaCounts() {
        return sodaMachine.getSodaCounts();
    }

}
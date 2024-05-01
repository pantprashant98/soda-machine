package com.example.sodamachine.service;

import com.example.sodamachine.model.State;

import java.util.Map;

public interface SodaMachineService {
    String insertQuarter();
    String removeQuarter();
    String pushButton(String sodaName);
    State getState();
    Map<String, Integer> getSodaCounts();
}
package com.example.sodamachine.model;

import com.example.sodamachine.util.FileLogger;

import java.util.HashMap;
import java.util.Map;

public class SodaMachine {
    private State state;
    private Map<String, Integer> sodaCounts;

    public SodaMachine(Map<String, Integer> initialSodaCounts) {
        this.state = State.SOLD_OUT;
        this.sodaCounts = new HashMap<>(initialSodaCounts);
        updateStateBasedOnSodaCounts();
    }
    private void updateStateBasedOnSodaCounts() {
        boolean hasSoda = sodaCounts.values().stream().anyMatch(count -> count > 0);
        state = hasSoda ? State.NO_QUARTER : State.SOLD_OUT;
    }

    public String insertQuarter() {
        String message = "";
        switch (state) {
            case SOLD_OUT:
                System.out.println("Soda machine is sold out.");
                message = "Soda machine is sold out.";
                break;
            case NO_QUARTER:
                state = State.HAS_QUARTER;
                System.out.println("Quarter inserted.");
                message = "Quarter inserted.";
                break;
            case HAS_QUARTER:
                System.out.println("Quarter already inserted.");
                message = "Quarter already inserted.";
                break;
            case SOLD:
                System.out.println("Please wait, we're already dispensing a soda.");
                message = "Soda machine is sold.";
                break;
        }
        return message;

    }

    public String removeQuarter() {
        if (state == State.HAS_QUARTER) {
            state = State.NO_QUARTER;
            System.out.println("Quarter removed.");
            return "Quarter removed.";
        } else {
            System.out.println("No quarter to remove.");
            return "No quarter to remove.";
        }
    }

    public String pushButton(String sodaName) {
        String message = "";
        switch (state) {
            case SOLD_OUT:
                System.out.println("Soda machine is sold out.");
                message = "Soda machine is sold out.";
                break;
            case NO_QUARTER:
                System.out.println("Please insert a quarter first.");
                message = "Please insert a quarter first.";
                break;
            case HAS_QUARTER:
                if (sodaCounts.getOrDefault(sodaName, 0) > 0) {
                    state = State.SOLD;
                    sodaCounts.put(sodaName, sodaCounts.get(sodaName) - 1);
                    dispenseSoda(sodaName);
                    logPurchase(sodaName);
                    updateStateBasedOnSodaCounts();
                    message = "Dispensing " + sodaName +  " from soda machine.";
                } else {
                    System.out.println("Sorry, we're out of " + sodaName + ".");
                    message = "Sorry, we're out of " + sodaName + ".";
                }
                break;
            case SOLD:
                System.out.println("Please wait, we're already dispensing a soda.");
                message = "Please wait, we're already dispensing a soda.";
                break;
        }
        return message;
    }

    private void dispenseSoda(String sodaName) {
        System.out.println("Dispensing " + sodaName + "...");
        state = State.NO_QUARTER;
        System.out.println("Enjoy your " + sodaName + "!");
    }


    private void logPurchase(String sodaName) {
        FileLogger.logPurchase(sodaName);
    }

    public State getState() {
        return state;
    }

    public Map<String, Integer> getSodaCounts() {
        return new HashMap<>(sodaCounts);
    }

    public void setSodaCounts(Map<String, Integer> newSodaCounts) {
        this.sodaCounts = new HashMap<>(newSodaCounts);
        updateStateBasedOnSodaCounts();
    }

}
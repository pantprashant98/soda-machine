package com.example.sodamachine.controller;

import com.example.sodamachine.service.SodaMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/soda-machine")
public class SodaMachineController {

    @Autowired
    private SodaMachineService sodaMachineService;

    @PostMapping("/insert-quarter")
    public ResponseEntity<String> insertQuarter() {
        return ResponseEntity.ok(sodaMachineService.insertQuarter());
    }

    @PostMapping("/remove-quarter")
    public ResponseEntity<String> removeQuarter() {
        return ResponseEntity.ok(sodaMachineService.removeQuarter());
    }

    @PostMapping("/push-button")
    public ResponseEntity<String> pushButton(@RequestParam String sodaName) {
        return ResponseEntity.ok(sodaMachineService.pushButton(sodaName));
    }

    @GetMapping("/state")
    public ResponseEntity<String> getState() {
        return ResponseEntity.ok(sodaMachineService.getState().toString());
    }

    @GetMapping("/soda-count")
    public ResponseEntity<Map<String, Integer>> getSodaCounts() {
        return ResponseEntity.ok(sodaMachineService.getSodaCounts());
    }
}
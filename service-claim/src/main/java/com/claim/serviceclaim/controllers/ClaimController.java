package com.claim.serviceclaim.controllers;

import com.claim.serviceclaim.dtos.claim.ClaimRequestDto;
import com.claim.serviceclaim.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createClaim(@RequestBody ClaimRequestDto claimRequestDto) {
        claimService.createClaim(claimRequestDto);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Created success!");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}

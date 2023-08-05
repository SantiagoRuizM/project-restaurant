package com.claim.serviceclaim.controllers;

import com.claim.serviceclaim.dtos.claim.ClaimActionRequestDto;
import com.claim.serviceclaim.dtos.claim.ClaimRequestDto;
import com.claim.serviceclaim.entities.ClaimEntity;
import com.claim.serviceclaim.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<ClaimEntity>> getAllClaims() {
        return new ResponseEntity<>(claimService.getAllClaims(), HttpStatus.OK);
    }

    @PutMapping("/update/accepted")
    public ResponseEntity<Map<String, String>> updateClaimAccepted(@RequestBody ClaimActionRequestDto claimActionRequestDto) {
        claimService.updateClaimAccepted(claimActionRequestDto);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Updated success!");
        map.put("decision", "Accepted!");
        map.put("actionClaim", claimActionRequestDto.getActionClaim());
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/rejected")
    public ResponseEntity<Map<String, String>> updateClaimRejected(@RequestBody ClaimActionRequestDto claimActionRequestDto) {
        claimService.updateClaimRejected(claimActionRequestDto);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Updated success!");
        map.put("decision", "Rejected!");
        map.put("actionClaim", claimActionRequestDto.getActionClaim());
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}

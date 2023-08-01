package com.claim.serviceclaim.validations;

import com.claim.serviceclaim.entities.ClaimEntity;
import com.claim.serviceclaim.exceptions.RecordNotFoundException;

import java.util.Optional;

public class ClaimValidations {

    public static void validateClaimPresent(Optional<ClaimEntity> claim, Long id) {
        if (claim.isEmpty()) throw new RecordNotFoundException("The claim with id " + id + ": was not found");
    }
}

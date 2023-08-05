package com.claim.serviceclaim.validations;

import com.claim.serviceclaim.entities.ClaimEntity;
import com.claim.serviceclaim.exceptions.FactRequiredException;
import com.claim.serviceclaim.exceptions.RecordNotFoundException;
import java.util.Optional;

public class ClaimValidations {

    public static void validateFactRequired(Object data, String typeData) {
        if (data == null) throw new FactRequiredException("The " + typeData + " is required");
    }

    public static void validateClaimPresent(Optional<ClaimEntity> claim, Long id) {
        if (claim.isEmpty()) throw new RecordNotFoundException("The claim with id " + id + ": was not found");
    }
}

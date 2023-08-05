package com.claim.serviceclaim.validations;

import com.claim.serviceclaim.entities.ClaimEntity;
import com.claim.serviceclaim.exceptions.FactRequiredException;
import com.claim.serviceclaim.exceptions.RecordNotFoundException;

import java.util.Optional;

public class ClaimValidationsTest {

    @org.junit.Test
    public void validateFactRequired_when_data_is_not_null() {
        ClaimValidations.validateFactRequired("", "");
    }

    @org.junit.Test(expected = FactRequiredException.class)
    public void validateFactRequired_when_data_is_null() {
        ClaimValidations.validateFactRequired(null, "");
    }

    @org.junit.Test
    public void validateClaimPresent_when_claim_for_id_is_present() {
        ClaimValidations.validateClaimPresent(Optional.of(new ClaimEntity()), 0L);
    }

    @org.junit.Test(expected = RecordNotFoundException.class)
    public void validateClaimPresent_when_claim_for_id_is_not_present() {
        ClaimValidations.validateClaimPresent(Optional.empty(), 0L);
    }
}
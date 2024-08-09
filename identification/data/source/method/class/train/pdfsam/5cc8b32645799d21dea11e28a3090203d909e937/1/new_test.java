    @Test
    public void alwaysValid() {
        FXValidationSupport<String> victim = new FXValidationSupport<>();
        victim.validate("Chuck");
        assertEquals(ValidationState.VALID, victim.validationStateProperty().get());
        victim.validate("");
        assertEquals(ValidationState.VALID, victim.validationStateProperty().get());
    }
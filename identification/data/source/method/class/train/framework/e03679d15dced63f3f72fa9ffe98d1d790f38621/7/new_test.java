    @Test
    public void alwaysPass() {
        Validator<String> alwaysPass = Validator.alwaysPass();
        ValidationResult result = alwaysPass.apply("foo", new ValueContext());
        assertFalse(result.isError());
    }
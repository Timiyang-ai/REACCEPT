    @Test
    public void from() {
        Validator<String> validator = Validator.from(Objects::nonNull,
                "Cannot be null");
        ValidationResult result = validator.apply(null, new ValueContext());
        assertTrue(result.isError());

        result = validator.apply("", new ValueContext());
        assertFalse(result.isError());
    }
    @Test
    public void ensureSimpleName_withNullString() {
        thrown.expect(NullPointerException.class);

        Formatters.ensureSimpleName(null);
    }
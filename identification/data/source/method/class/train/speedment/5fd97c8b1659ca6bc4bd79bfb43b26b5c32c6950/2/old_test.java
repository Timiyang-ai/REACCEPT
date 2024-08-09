    @ParameterizedTest
    @ValueSource(strings = {"a", "bc", "foo", "test"})
    void isNotNull(String input) {
        assertNotNull(instance.isNotNull());
        assertTrue(instance.isNotNull(input));
    }
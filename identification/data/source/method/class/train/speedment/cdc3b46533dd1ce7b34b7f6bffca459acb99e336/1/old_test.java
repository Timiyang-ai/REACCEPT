    @Test
    void compose() {
        assertThrows(NullPointerException.class, () -> DEFAULT_TO.compose(null));

        ToBoolean<Boolean> composed = DEFAULT_TO.compose(Object::toString);

        assertNotNull(composed);
    }
    @Test
    public void reject() {
        assertThrows(InvalidClassException.class,
                () -> assertSerialization(
                willClose(new ValidatingObjectInputStream(testStream))
                .accept(Long.class)
                .reject(MockSerializedClass.class, Integer.class)
        ));
    }
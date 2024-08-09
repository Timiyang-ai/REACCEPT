    @Test
    public void min_notSupported() {
        Query<TestEntity> query = box.query().build();
        String exceptionMessage = "This operation is not supported for Property ";
        assertUnsupported(() -> query.property(simpleBoolean).min(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleByteArray).min(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleString).min(), exceptionMessage);

        String exceptionMessage2 = "Use double based min (e.g. `minDouble()`) instead. This operation is not supported for Property ";
        assertUnsupported(() -> query.property(simpleFloat).min(), exceptionMessage2);
        assertUnsupported(() -> query.property(simpleDouble).min(), exceptionMessage2);
    }
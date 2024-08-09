    @Test
    public void max_notSupported() {
        Query<TestEntity> query = box.query().build();
        String exceptionMessage = "This operation is not supported for Property ";
        assertUnsupported(() -> query.property(simpleBoolean).max(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleByteArray).max(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleString).max(), exceptionMessage);

        String exceptionMessage2 = "Use double based max (e.g. `maxDouble()`) instead. This operation is not supported for Property ";
        assertUnsupported(() -> query.property(simpleFloat).max(), exceptionMessage2);
        assertUnsupported(() -> query.property(simpleDouble).max(), exceptionMessage2);
    }
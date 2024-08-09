    @Test
    public void minDouble_notSupported() {
        Query<TestEntity> query = box.query().build();
        String exceptionMessage = "Not a floating point type. This operation is not supported for Property ";
        assertUnsupported(() -> query.property(simpleBoolean).minDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleByteArray).minDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleString).minDouble(), exceptionMessage);

        assertUnsupported(() -> query.property(simpleByte).minDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleShort).minDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleInt).minDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleLong).minDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleShortU).minDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleIntU).minDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleLongU).minDouble(), exceptionMessage);
    }
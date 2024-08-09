    @Test
    public void maxDouble_notSupported() {
        Query<TestEntity> query = box.query().build();
        String exceptionMessage = "Not a floating point type. This operation is not supported for Property ";
        assertUnsupported(() -> query.property(simpleBoolean).maxDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleByteArray).maxDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleString).maxDouble(), exceptionMessage);

        assertUnsupported(() -> query.property(simpleByte).maxDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleShort).maxDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleInt).maxDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleLong).maxDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleShortU).maxDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleIntU).maxDouble(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleLongU).maxDouble(), exceptionMessage);
    }
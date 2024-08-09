    @Test
    public void avg_notSupported() {
        Query<TestEntity> query = box.query().build();
        String exceptionMessage = "Cannot calculate sum. This function is for integer types only. This operation is not supported for Property ";
        assertUnsupported(() -> query.property(simpleByteArray).avg(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleString).avg(), exceptionMessage);
    }
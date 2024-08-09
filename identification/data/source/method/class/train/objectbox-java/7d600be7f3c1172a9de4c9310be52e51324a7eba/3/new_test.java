    @Test
    public void sum_notSupported() {
        Query<TestEntity> query = box.query().build();
        String exceptionMessage = "Cannot calculate sum. This function is for integer types only. This operation is not supported for Property ";
        assertUnsupported(() -> query.property(simpleByteArray).sum(), exceptionMessage);
        assertUnsupported(() -> query.property(simpleString).sum(), exceptionMessage);

        String exceptionMessage2 = "Please use the double based sum instead. This operation is not supported for Property ";
        assertUnsupported(() -> query.property(simpleFloat).sum(), exceptionMessage2);
        assertUnsupported(() -> query.property(simpleDouble).sum(), exceptionMessage2);
    }
    @Test
    public void getBlackObject() {
        assertObjectHasNoNullFields(pointContainerAccessor.getBlackObject(TypeTag.NULL));
    }
    @Test
    public void getBlackAccessor() {
        PointContainer foo = pointContainerAccessor.getBlackObject(TypeTag.NULL);
        ObjectAccessor<PointContainer> objectAccessor =
                pointContainerAccessor.getBlackAccessor(TypeTag.NULL);
        assertEquals(foo, objectAccessor.get());
    }
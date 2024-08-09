    @Test
    public void getRedAccessor() {
        PointContainer foo = pointContainerAccessor.getRedObject(TypeTag.NULL);
        ObjectAccessor<PointContainer> objectAccessor =
                pointContainerAccessor.getRedAccessor(TypeTag.NULL);
        assertEquals(foo, objectAccessor.get());
    }
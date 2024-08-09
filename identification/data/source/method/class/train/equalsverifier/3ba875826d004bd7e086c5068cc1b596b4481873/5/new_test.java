    @Test
    public void getDefaultValuesAccessor_withNoNonnullValues() {
        ObjectAccessor<DefaultValues> objectAccessor =
                defaultValuesClassAccessor.getDefaultValuesAccessor(
                        TypeTag.NULL, new HashSet<>(), defaultValuesAnnotationCache);
        DefaultValues foo = objectAccessor.get();
        assertEquals(null, foo.s);
        // The rest is tested in getDefaultValuesObject
    }
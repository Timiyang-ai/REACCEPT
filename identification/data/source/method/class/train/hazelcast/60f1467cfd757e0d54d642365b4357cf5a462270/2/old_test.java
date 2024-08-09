    @Test
    public void setFactoryClassName() {
        assertEquals("factoryClassName", cfgNonNullFactoryClassName.getFactoryClassName());
        assertEquals(new MapStoreConfig().setFactoryClassName("factoryClassName"), cfgNonNullFactoryClassName);
    }
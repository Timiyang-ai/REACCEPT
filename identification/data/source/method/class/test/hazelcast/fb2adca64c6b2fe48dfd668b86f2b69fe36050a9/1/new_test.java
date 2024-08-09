    @Test
    public void setClassName() {
        assertEquals("some.class", cfgNonNullClassName.getClassName());
        assertEquals(new MapStoreConfig().setClassName("some.class"), cfgNonNullClassName);
    }
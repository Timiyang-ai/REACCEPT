    @Test
    public void defaultStaticField() {
        StaticContainer foo = new StaticContainer();
        getAccessorFor(foo, "field").defaultStaticField();
        assertNull(StaticContainer.field);
    }
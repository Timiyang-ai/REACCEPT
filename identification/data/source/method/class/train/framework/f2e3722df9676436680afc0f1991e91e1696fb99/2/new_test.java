    @Test
    public void setValue() {
        MethodProperty<String> mp = new MethodProperty<String>(testObject,
                "street");
        mp.setValue("Foo street");
        assertEquals("Foo street", testObject.getStreet());
    }
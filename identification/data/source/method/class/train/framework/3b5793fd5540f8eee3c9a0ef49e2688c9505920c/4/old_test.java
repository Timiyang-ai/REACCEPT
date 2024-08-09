    @Test
    public void getValue() {
        MethodProperty<String> mp = new MethodProperty<String>(testObject,
                "street");
        assertEquals("some street", mp.getValue());
    }
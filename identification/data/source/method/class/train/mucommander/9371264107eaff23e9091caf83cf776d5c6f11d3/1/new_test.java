@Test
    public void testFlatten() {
        assert null == StringUtils.flatten(null, "*");
        assert null == StringUtils.flatten(null);
    }
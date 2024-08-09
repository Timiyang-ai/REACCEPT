    @Test
    public void getClassProperties_null() {
        assert ClassUtils.getClassProperties(null).size() == 0;
    }
@Test
    public void testGetValue(){
        assertEquals("5,8,7,6", ResourceBundleUtil.getValue(BASE_NAME, "config_test_array"));
        assertEquals("5,8,7,6", ResourceBundleUtil.getValue("messages.feilong-core-test", "config_test_array"));
    }
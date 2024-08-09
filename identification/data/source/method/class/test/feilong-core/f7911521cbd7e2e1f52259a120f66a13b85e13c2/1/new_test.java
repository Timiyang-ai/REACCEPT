@Test
    public void testGetValue(){
        assertEquals("5,8,7,6", getValue(BASE_NAME, "config_test_array"));
        assertEquals("5,8,7,6", getValue("messages.feilong-core-test", "config_test_array"));
    }
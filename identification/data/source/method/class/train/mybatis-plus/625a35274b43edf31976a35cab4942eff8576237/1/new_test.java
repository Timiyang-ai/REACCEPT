    @Test
    void isCapitalModeTest(){
        Assertions.assertFalse(StringUtils.isCapitalMode("test"));
        Assertions.assertFalse(StringUtils.isCapitalMode("Test"));
        Assertions.assertFalse(StringUtils.isCapitalMode("teSt"));
        Assertions.assertTrue(StringUtils.isCapitalMode("TEST"));
    }
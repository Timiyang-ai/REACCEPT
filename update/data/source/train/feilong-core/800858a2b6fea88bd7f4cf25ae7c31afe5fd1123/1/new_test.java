@Test
    public void testIsBefore(){
        assertEquals(true, DateUtil.isBefore(FROMSTRING, TOSTRING, DatePattern.COMMON_DATE));
        assertEquals(false, DateUtil.isBefore("2011-05-01", "2011-04-01", DatePattern.COMMON_DATE));
    }
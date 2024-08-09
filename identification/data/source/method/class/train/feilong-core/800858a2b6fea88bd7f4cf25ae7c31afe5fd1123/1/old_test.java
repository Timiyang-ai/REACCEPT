@Test
    public void testIsBefore(){
        boolean isBefore = DateUtil.isBefore(FROMSTRING, TOSTRING, DatePattern.COMMON_DATE);
        LOGGER.debug(String.valueOf(isBefore));
    }
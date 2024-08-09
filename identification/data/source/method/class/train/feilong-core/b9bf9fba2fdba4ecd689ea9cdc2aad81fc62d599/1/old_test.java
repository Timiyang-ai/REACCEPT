@Test
    public final void testGetExtentYesterday(){
        Date[] dates = DateExtensionUtil.getExtentYesterday();
        LOGGER.debug(JsonUtil.format(dates));
    }
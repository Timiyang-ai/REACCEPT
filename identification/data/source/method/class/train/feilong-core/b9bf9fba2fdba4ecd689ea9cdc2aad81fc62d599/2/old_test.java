@Test
    public final void testGetExtentToday(){
        Date[] dates = DateExtensionUtil.getExtentToday();
        LOGGER.debug(JsonUtil.format(dates));
    }
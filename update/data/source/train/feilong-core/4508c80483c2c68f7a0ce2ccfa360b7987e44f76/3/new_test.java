@Test
    public void testGetIntervalHour1(){
        log.debug(StringUtil.format(
                        "%05d",
                        DateUtil.getIntervalHour(
                                        DateUtil.string2Date("2014-01-01 00:00:00", DatePattern.COMMON_DATE_AND_TIME),
                                        DateUtil.string2Date("2014-02-01 00:00:00", DatePattern.COMMON_DATE_AND_TIME)))

                        + "");
    }
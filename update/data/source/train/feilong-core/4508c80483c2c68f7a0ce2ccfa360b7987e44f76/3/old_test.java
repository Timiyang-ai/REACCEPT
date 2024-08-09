@Test
    public final void getIntervalHour(){
        //        Date startDate = DateUtil.string2Date("2013-01-01 00:00:00", DatePattern.COMMON_DATE_AND_TIME);
        //        log.debug(DateUtil.getIntervalHour(startDate, NOW) + "");
        //        log.debug(DateUtil.getIntervalHour(startDate, DateUtil.string2Date("2113-01-01 00:00:00", DatePattern.COMMON_DATE_AND_TIME)) + "");
        //        log.debug(DateUtil.getIntervalHour(startDate, DateUtil.string2Date("3113-01-01 00:00:00", DatePattern.COMMON_DATE_AND_TIME)) + "");
        log.debug(StringUtil.format(
                        "%05d",
                        DateUtil.getIntervalHour(
                                        DateUtil.string2Date("2014-01-01 00:00:00", DatePattern.COMMON_DATE_AND_TIME),
                                        DateUtil.string2Date("2014-02-01 00:00:00", DatePattern.COMMON_DATE_AND_TIME)))

                        + "");
    }
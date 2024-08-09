@Test
    public void testToPrettyDateString(){
        LOGGER.debug(toPrettyDateString(DateUtil.toDate("2012-10-18 13:55:00", COMMON_DATE_AND_TIME)));
        LOGGER.debug(toPrettyDateString(DateUtil.toDate("2012-10-18 14:14:22", COMMON_DATE_AND_TIME)));
        LOGGER.debug(toPrettyDateString(DateUtil.toDate("2012-10-18 14:15:22", COMMON_DATE_AND_TIME)));
        LOGGER.debug(toPrettyDateString(DateUtil.toDate("2012-10-17 14:15:02", COMMON_DATE_AND_TIME)));
        LOGGER.debug(toPrettyDateString(DateUtil.toDate("2012-10-16 14:15:02", COMMON_DATE_AND_TIME)));
        LOGGER.debug(toPrettyDateString(DateUtil.toDate("2012-10-15 14:15:02", COMMON_DATE_AND_TIME)));
        LOGGER.debug(toPrettyDateString(DateUtil.toDate("2012-09-15 14:15:02", COMMON_DATE_AND_TIME)));
        LOGGER.debug(toPrettyDateString(DateUtil.toDate("2015-08-02 14:15:02", COMMON_DATE_AND_TIME)));
        LOGGER.debug(toPrettyDateString(DateUtil.toDate("2015-7-30 13:00:00", COMMON_DATE_AND_TIME)));
    }
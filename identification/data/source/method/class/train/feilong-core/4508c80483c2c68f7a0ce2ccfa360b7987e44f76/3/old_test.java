@Test
    public void testGetIntervalDay(){
        // Date now = DateUtil.convertStringToDate(fromString, DateUtil.pattern_commonWithTime);
        // Date date = DateUtil.convertStringToDate(toString, DateUtil.pattern_commonWithTime);
        // log.debug(DateUtil.getIntervalDay(now, date));
        String fromString = "2008-12-1";
        String toString = "2008-9-29";
        int intervalDay = DateUtil.getIntervalDay(fromString, toString, DatePattern.COMMON_DATE);
        log.debug(intervalDay + "");
    }
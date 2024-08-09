@Test
    public void testToDate() throws ParseException{
        //logDate(DateUtils.parseDate("2016-02-33", DatePattern.COMMON_DATE));
        Date date = DateUtil.toDate("2016-02-33", DatePattern.COMMON_DATE);
        logDate(date);

        // 商品上线时间
        logDate(DateUtil.toDate("20130102140806000", DatePattern.TIMESTAMP_WITH_MILLISECOND));
    }
@Test
    public void testToDate(){
        logDate(DateUtil.toDate("2016-06-28T01:21:12-0800", "yyyy-MM-dd'T'HH:mm:ssZ"));
        logDate(DateUtil.toDate("2016-06-28T01:21:12+0800", "yyyy-MM-dd'T'HH:mm:ssZ"));

        logDate(DateUtil.toDate("2016-02-33", DatePattern.COMMON_DATE));

        // 商品上线时间
        logDate(DateUtil.toDate("20130102140806000", DatePattern.TIMESTAMP_WITH_MILLISECOND));
    }
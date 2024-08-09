@Test
    public void testToDate(){
        logDate(DateUtil.toDate(FROMSTRING, DatePattern.COMMON_DATE));

        // 商品上线时间
        logDate(DateUtil.toDate("20130102140806000", DatePattern.TIMESTAMP_WITH_MILLISECOND));
    }
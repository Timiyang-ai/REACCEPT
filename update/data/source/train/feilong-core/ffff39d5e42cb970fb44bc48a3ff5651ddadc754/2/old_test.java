@Test
    public void testString2Date(){
        logDate(DateUtil.string2Date(FROMSTRING, DatePattern.COMMON_DATE));

        // 商品上线时间
        logDate(DateUtil.string2Date("20130102140806000", DatePattern.TIMESTAMP_WITH_MILLISECOND));
    }
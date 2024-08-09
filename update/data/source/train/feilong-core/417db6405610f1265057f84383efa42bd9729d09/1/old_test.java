@Test
    public void tokenizeToStringArray(){
        String str = "jin.xin  h hhaha ,lala;feilong;jin.xin  h hhaha ,lala;feilong;jin.xin  h hhaha ,lala;feilong;jin.xin  h hhaha ,lala;feilong;jin.xin  h hhaha ,lala;feilong;jin.xin  h hhaha ,lala;feilong;jin.xin  h hhaha ,lala;feilong";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1; ++i){
            sb.append(str);
        }
        str = sb.toString();
        String delimiters = ";, .";
        String[] tokenizeToStringArray = StringUtil.tokenizeToStringArray(str, delimiters);
        LOGGER.info(JsonUtil.format(tokenizeToStringArray));
        Date beginDate = new Date();

        for (int i = 0; i < 100000; ++i){
            StringUtil.tokenizeToStringArray(str, delimiters);
        }

        Date endDate = new Date();
        LOGGER.info("time:{}", DateUtil.getIntervalTime(beginDate, endDate));
    }
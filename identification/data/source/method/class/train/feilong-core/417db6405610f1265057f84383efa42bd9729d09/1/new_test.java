@Test
    public void tokenizeToStringArray(){
        Date beginDate = new Date();

        for (int i = 0; i < 100000; ++i){
            tokenizeToStringArray1();
        }

        Date endDate = new Date();
        LOGGER.info("time:{}", DateUtil.getIntervalTime(beginDate, endDate));
    }
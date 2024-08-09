@Test
    public void createRandomWithLength(){
        for (int i = 0, j = 100; i < j; ++i){
            LOGGER.info(RandomUtil.createRandomWithLength(2) + "");
        }
    }
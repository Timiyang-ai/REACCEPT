@Test
    public void createRandomWithLength(){
        for (int i = 0, j = 100; i < j; ++i){
            LOGGER.debug(RandomUtil.createRandomWithLength(2) + "");
        }
    }
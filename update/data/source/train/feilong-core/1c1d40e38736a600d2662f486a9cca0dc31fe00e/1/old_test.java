@Test
    public void toIterator(){
        String[] array = { "1", "223" };
        LOGGER.debug(JsonUtil.format(toIterator(array)));

        int[] arrays = { 1, 2 };
        LOGGER.debug(JsonUtil.format(toIterator(arrays)));
        LOGGER.debug(JsonUtil.format(new ArrayIterator(arrays)));
        LOGGER.debug(JsonUtil.format(new ArrayIterator(array)));
        //LOGGER.debug(JsonUtil.format(new ArrayIterator(null)));
    }
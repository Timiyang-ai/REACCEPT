@Test
    public void testCreateRandom2(){
        LOGGER.debug("" + RandomUtil.createRandom(10, 20));
        LOGGER.debug("" + RandomUtil.createRandom(0, 800));

        assertEquals(800L, RandomUtil.createRandom(800, 800));
    }
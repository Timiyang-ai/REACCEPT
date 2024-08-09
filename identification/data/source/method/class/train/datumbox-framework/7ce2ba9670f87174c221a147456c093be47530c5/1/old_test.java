@Test
    public void testShuffle() {
        TestUtils.log(this.getClass(), "shuffle");
        RandomSingleton.getInstance().setSeed(TestConfiguration.RANDOM_SEED);
        Integer[] result = {1,2,3,4,5};
        Integer[] expResult = {2,3,4,5,1};
        PHPfunctions.shuffle(result);
        assertArrayEquals(expResult, result);
    }
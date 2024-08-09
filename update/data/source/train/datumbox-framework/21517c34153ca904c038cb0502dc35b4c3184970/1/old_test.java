@Test
    public void testShuffle() {
        TestUtils.log(this.getClass(), "shuffle");
        RandomValue.randomGenerator = new Random(42);
        Integer[] result = {1,2,3,4,5};
        Integer[] expResult = {2,3,4,5,1};
        PHPfunctions.shuffle(result);
        assertArrayEquals(expResult, result);
    }
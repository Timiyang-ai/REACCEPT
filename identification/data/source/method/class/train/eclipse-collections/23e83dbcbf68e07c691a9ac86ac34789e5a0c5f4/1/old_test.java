    @Test
    public void sumByDouble()
    {
        Integer[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ObjectDoubleMap<Integer> result = ArrayIterate.sumByDouble(values, d -> d % 2, e -> e);
        Assert.assertEquals(25.0d, result.get(1), 0.0);
        Assert.assertEquals(30.0d, result.get(0), 0.0);
    }
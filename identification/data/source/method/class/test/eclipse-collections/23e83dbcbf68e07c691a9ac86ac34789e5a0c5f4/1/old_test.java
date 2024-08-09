    @Test
    public void sumByFloat()
    {
        Integer[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ObjectDoubleMap<Integer> result = ArrayIterate.sumByFloat(values, f -> f % 2, e -> e);
        Assert.assertEquals(25.0f, result.get(1), 0.0);
        Assert.assertEquals(30.0f, result.get(0), 0.0);
    }
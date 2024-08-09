    @Test
    public void sumByLong()
    {
        Integer[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ObjectLongMap<Integer> result = ArrayIterate.sumByLong(values, l -> l % 2, e -> e);
        Assert.assertEquals(25, result.get(1));
        Assert.assertEquals(30, result.get(0));
    }
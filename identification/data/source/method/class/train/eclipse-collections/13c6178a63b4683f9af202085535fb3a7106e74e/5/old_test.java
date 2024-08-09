    @Test
    public void distinct()
    {
        List<Integer> result = FastList.newList();
        ArrayIterate.distinct(new Integer[]{5, 3, 1, 5, 7, 1}, result);
        Assert.assertEquals(FastList.newListWith(5, 3, 1, 7), result);
        result.clear();
        ArrayIterate.distinct(INTEGER_ARRAY, result);
        Assert.assertEquals(FastList.newListWith(INTEGER_ARRAY), result);

        MutableList<Integer> list = ArrayIterate.distinct(new Integer[]{5, 3, 1, 5, 7, 1});
        Assert.assertEquals(FastList.newListWith(5, 3, 1, 7), list);
    }
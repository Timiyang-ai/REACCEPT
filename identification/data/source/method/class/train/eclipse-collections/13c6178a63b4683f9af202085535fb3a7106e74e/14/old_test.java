    @Test
    public void reject()
    {
        MutableList<Integer> result = MapIterate.reject(
                newLittleMap(),
                Predicates.greaterThanOrEqualTo(2));
        Assert.assertEquals(FastList.newListWith(1), result);
    }
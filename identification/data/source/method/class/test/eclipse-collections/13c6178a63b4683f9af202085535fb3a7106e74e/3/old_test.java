    @Test
    public void drop()
    {
        List<Integer> list = this.getIntegerList();
        Iterable<Integer> iterable = new IterableAdapter<>(list);
        Assert.assertEquals(FastList.newListWith(5, 4, 3, 2, 1), Iterate.drop(iterable, 0));
        Assert.assertEquals(FastList.newListWith(4, 3, 2, 1), Iterate.drop(iterable, 1));
        Assert.assertEquals(FastList.newListWith(3, 2, 1), Iterate.drop(iterable, 2));
        Assert.assertEquals(FastList.newListWith(1), Iterate.drop(iterable, 4));
        Verify.assertEmpty(Iterate.drop(iterable, 5));
        Verify.assertEmpty(Iterate.drop(iterable, 6));
        Verify.assertEmpty(Iterate.drop(iterable, Integer.MAX_VALUE));
    }
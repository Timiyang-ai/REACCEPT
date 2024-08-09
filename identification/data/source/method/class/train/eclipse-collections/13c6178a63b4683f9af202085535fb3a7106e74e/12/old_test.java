    @Test
    public void take()
    {
        List<Integer> list = this.getIntegerList();
        Iterable<Integer> iterable = new IterableAdapter<>(list);
        Verify.assertEmpty(Iterate.take(iterable, 0));
        Assert.assertEquals(FastList.newListWith(5), Iterate.take(iterable, 1));
        Assert.assertEquals(FastList.newListWith(5, 4), Iterate.take(iterable, 2));
        Assert.assertEquals(list, Iterate.take(iterable, 5));
        Assert.assertEquals(list, Iterate.take(iterable, 6));
        Assert.assertEquals(list, Iterate.take(iterable, Integer.MAX_VALUE));
        Assert.assertNotSame(iterable, Iterate.take(iterable, Integer.MAX_VALUE));
    }
    @Test
    public void contains()
    {
        Assert.assertTrue(IntInterval.zero().contains(0));
        Assert.assertTrue(IntInterval.oneTo(5).containsAll(1, 5));
        Assert.assertTrue(IntInterval.oneTo(5).containsNone(6, 7));
        Assert.assertFalse(IntInterval.oneTo(5).containsAll(1, 6));
        Assert.assertFalse(IntInterval.oneTo(5).containsNone(1, 6));
        Assert.assertFalse(IntInterval.oneTo(5).contains(0));
        Assert.assertTrue(IntInterval.fromTo(-1, -5).containsAll(-1, -5));
        Assert.assertFalse(IntInterval.fromTo(-1, -5).contains(1));

        Assert.assertTrue(IntInterval.zero().contains(Integer.valueOf(0)));
        Assert.assertFalse(IntInterval.oneTo(5).contains(Integer.valueOf(0)));
        Assert.assertFalse(IntInterval.fromTo(-1, -5).contains(Integer.valueOf(1)));
    }
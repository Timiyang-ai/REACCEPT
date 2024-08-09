    @Test
    public void containsAll()
    {
        Assert.assertTrue(IntInterval.fromTo(1, 3).containsAll(1, 2, 3));
        Assert.assertFalse(IntInterval.fromTo(1, 3).containsAll(1, 2, 4));
    }
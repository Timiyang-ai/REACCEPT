    @Test
    public void toReversed()
    {
        IntInterval forward = IntInterval.oneTo(5);
        IntInterval reverse = forward.toReversed();
        Assert.assertEquals(IntArrayList.newListWith(5, 4, 3, 2, 1), reverse);
    }
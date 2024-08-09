    @Test
    public void take()
    {
        Assert.assertEquals(
                ListIterate.take(Interval.zeroTo(0), 0),
                ArrayIterate.take(Interval.zeroTo(0).toArray(), 0));
        Assert.assertEquals(
                ListIterate.take(Interval.zeroTo(5), 1),
                ArrayIterate.take(Interval.zeroTo(5).toArray(), 1));
        Assert.assertEquals(
                ListIterate.take(Interval.zeroTo(5), 2),
                ArrayIterate.take(Interval.zeroTo(5).toArray(), 2));
        Assert.assertEquals(
                ListIterate.take(Interval.zeroTo(0), 5),
                ArrayIterate.take(Interval.zeroTo(0).toArray(), 5));
        Assert.assertEquals(
                ListIterate.take(Interval.zeroTo(5), 5),
                ArrayIterate.take(Interval.zeroTo(5).toArray(), 5));
        Assert.assertEquals(
                ListIterate.take(Interval.zeroTo(10), 5),
                ArrayIterate.take(Interval.zeroTo(10).toArray(), 5));
        Assert.assertEquals(
                ListIterate.take(Interval.zeroTo(10), 15),
                ArrayIterate.take(Interval.zeroTo(10).toArray(), 15));
        Assert.assertEquals(
                ListIterate.take(Interval.zeroTo(10), Integer.MAX_VALUE),
                ArrayIterate.take(Interval.zeroTo(10).toArray(), Integer.MAX_VALUE));
    }
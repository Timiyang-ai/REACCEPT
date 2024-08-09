    @Test
    public void drop()
    {
        Assert.assertEquals(
                ListIterate.drop(Interval.zeroTo(5).toList(), 0),
                ArrayIterate.drop(Interval.zeroTo(5).toList().toArray(), 0));
        Assert.assertEquals(
                ListIterate.drop(Interval.zeroTo(5).toList(), 1),
                ArrayIterate.drop(Interval.zeroTo(5).toList().toArray(), 1));
        Assert.assertEquals(
                ListIterate.drop(Interval.zeroTo(0).toList(), 5),
                ArrayIterate.drop(Interval.zeroTo(0).toList().toArray(), 5));
        Assert.assertEquals(
                ListIterate.drop(Interval.zeroTo(5), 5),
                ArrayIterate.drop(Interval.zeroTo(5).toArray(), 5));
        Assert.assertEquals(
                ListIterate.drop(Interval.zeroTo(10), 5),
                ArrayIterate.drop(Interval.zeroTo(10).toArray(), 5));
        Assert.assertEquals(
                ListIterate.drop(Interval.zeroTo(10), 15),
                ArrayIterate.drop(Interval.zeroTo(10).toArray(), 15));
        Assert.assertEquals(
                ListIterate.drop(Interval.zeroTo(10), Integer.MAX_VALUE),
                ArrayIterate.drop(Interval.zeroTo(10).toArray(), Integer.MAX_VALUE));
    }
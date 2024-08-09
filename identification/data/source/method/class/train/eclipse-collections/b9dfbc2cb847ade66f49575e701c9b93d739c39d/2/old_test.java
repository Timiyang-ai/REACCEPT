    @Test
    public void each()
    {
        MutableIntList list1 = IntLists.mutable.empty();
        IntInterval interval1 = IntInterval.oneTo(5);
        interval1.each(list1::add);
        Assert.assertEquals(list1, interval1);
        IntInterval interval2 = IntInterval.fromTo(5, 1);
        MutableIntList list2 = IntLists.mutable.empty();
        interval2.each(list2::add);
        Assert.assertEquals(list2, interval2);
    }
    @Test
    public void partition()
    {
        ArrayList<Integer> smallList = new ArrayList<>(Interval.oneTo(99));
        PartitionMutableList<Integer> smallPartition = ArrayListIterate.partition(smallList, Predicates.in(Interval.oneToBy(99, 2)));
        Assert.assertEquals(Interval.oneToBy(99, 2), smallPartition.getSelected());
        Assert.assertEquals(Interval.fromToBy(2, 98, 2), smallPartition.getRejected());

        ArrayList<Integer> bigList = new ArrayList<>(Interval.oneTo(101));
        PartitionMutableList<Integer> bigPartition = ArrayListIterate.partition(bigList, Predicates.in(Interval.oneToBy(101, 2)));
        Assert.assertEquals(Interval.oneToBy(101, 2), bigPartition.getSelected());
        Assert.assertEquals(Interval.fromToBy(2, 100, 2), bigPartition.getRejected());
    }
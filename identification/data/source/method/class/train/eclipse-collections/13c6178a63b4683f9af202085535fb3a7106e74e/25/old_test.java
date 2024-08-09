    @Test
    public void take()
    {
        MutableList<Integer> integers = this.getIntegerList();
        Verify.assertListsEqual(integers.take(0), RandomAccessListIterate.take(integers, 0));
        Verify.assertListsEqual(integers.take(1), RandomAccessListIterate.take(integers, 1));
        Verify.assertListsEqual(integers.take(2), RandomAccessListIterate.take(integers, 2));
        Verify.assertListsEqual(integers.take(5), RandomAccessListIterate.take(integers, 5));
        Verify.assertListsEqual(
                integers.take(integers.size() - 1),
                RandomAccessListIterate.take(integers, integers.size() - 1));
        Verify.assertListsEqual(integers.take(integers.size()), RandomAccessListIterate.take(integers, integers.size()));
        Verify.assertListsEqual(integers.take(10), RandomAccessListIterate.take(integers, 10));
        Verify.assertListsEqual(integers.take(Integer.MAX_VALUE), RandomAccessListIterate.take(integers, Integer.MAX_VALUE));
        Verify.assertListsEqual(FastList.newList(), RandomAccessListIterate.take(Lists.fixedSize.of(), 2));
    }
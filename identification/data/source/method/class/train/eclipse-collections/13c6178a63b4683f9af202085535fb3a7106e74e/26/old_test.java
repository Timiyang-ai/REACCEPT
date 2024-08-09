    @Test
    public void drop()
    {
        MutableList<Integer> integers = this.getIntegerList();
        Verify.assertListsEqual(integers.drop(0), RandomAccessListIterate.drop(integers, 0));
        Verify.assertListsEqual(integers.drop(1), RandomAccessListIterate.drop(integers, 1));
        Verify.assertListsEqual(integers.drop(2), RandomAccessListIterate.drop(integers, 2));
        Verify.assertListsEqual(integers.drop(5), RandomAccessListIterate.drop(integers, 5));
        Verify.assertListsEqual(integers.drop(6), RandomAccessListIterate.drop(integers, 6));
        Verify.assertListsEqual(
                integers.drop(integers.size() - 1),
                RandomAccessListIterate.drop(integers, integers.size() - 1));
        Verify.assertListsEqual(integers.drop(integers.size()), RandomAccessListIterate.drop(integers, integers.size()));
        Verify.assertListsEqual(FastList.newList(), RandomAccessListIterate.drop(Lists.fixedSize.of(), 0));
        Verify.assertListsEqual(FastList.newList(), RandomAccessListIterate.drop(Lists.fixedSize.of(), 2));
        Verify.assertListsEqual(integers.drop(Integer.MAX_VALUE), RandomAccessListIterate.drop(integers, Integer.MAX_VALUE));
    }
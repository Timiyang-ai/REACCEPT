    @Test
    public void take()
    {
        ArrayList<Integer> list = this.getIntegerList();
        Verify.assertListsEqual(FastList.newList(list).take(0), ArrayListIterate.take(list, 0));
        Verify.assertListsEqual(FastList.newList(list).take(1), ArrayListIterate.take(list, 1));
        Verify.assertListsEqual(FastList.newList(list).take(2), ArrayListIterate.take(list, 2));
        Verify.assertListsEqual(FastList.newList(list).take(5), ArrayListIterate.take(list, 5));
        Verify.assertListsEqual(FastList.newList(list).take(list.size() - 1), ArrayListIterate.take(list, list.size() - 1));
        Verify.assertListsEqual(FastList.newList(list).take(list.size()), ArrayListIterate.take(list, list.size()));
        Verify.assertListsEqual(FastList.newList().take(2), ArrayListIterate.take(new ArrayList<>(), 2));
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.addAll(Interval.oneTo(120));
        Verify.assertListsEqual(FastList.newList(list1).take(125), ArrayListIterate.take(list1, 125));
        Verify.assertListsEqual(FastList.newList(list1).take(Integer.MAX_VALUE), ArrayListIterate.take(list1, Integer.MAX_VALUE));
    }
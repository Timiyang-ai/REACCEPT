    @Test
    public void drop()
    {
        ArrayList<Integer> list = this.getIntegerList();
        MutableList<Integer> expected = FastList.newList(list);
        Verify.assertListsEqual(expected.drop(0), ArrayListIterate.drop(list, 0));
        Verify.assertListsEqual(expected.drop(1), ArrayListIterate.drop(list, 1));
        Verify.assertListsEqual(expected.drop(2), ArrayListIterate.drop(list, 2));
        Verify.assertListsEqual(expected.drop(5), ArrayListIterate.drop(list, 5));
        Verify.assertListsEqual(expected.drop(6), ArrayListIterate.drop(list, 6));
        Verify.assertListsEqual(expected.drop(list.size() - 1), ArrayListIterate.drop(list, list.size() - 1));
        Verify.assertListsEqual(expected.drop(list.size()), ArrayListIterate.drop(list, list.size()));
        Verify.assertListsEqual(FastList.newList(), ArrayListIterate.drop(new ArrayList<>(), 2));
        Verify.assertListsEqual(expected.drop(Integer.MAX_VALUE), ArrayListIterate.drop(list, Integer.MAX_VALUE));

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.addAll(Interval.oneTo(120));
        Verify.assertListsEqual(FastList.newList(list1).drop(100), ArrayListIterate.drop(list1, 100));
        Verify.assertListsEqual(FastList.newList(list1).drop(125), ArrayListIterate.drop(list1, 125));
        Verify.assertListsEqual(FastList.newList(list1).drop(Integer.MAX_VALUE), ArrayListIterate.drop(list1, Integer.MAX_VALUE));
    }
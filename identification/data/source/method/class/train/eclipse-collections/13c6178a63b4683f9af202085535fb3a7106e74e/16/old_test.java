    @Test
    public void distinct()
    {
        MutableList<Integer> list = FastList.newListWith(5, 2, 6, 2, 3, 5, 2);
        MutableList<Integer> actualList = FastList.newList();
        RandomAccessListIterate.distinct(list, actualList);
        Verify.assertListsEqual(FastList.newListWith(5, 2, 6, 3), actualList);
        Verify.assertSize(7, list);
    }
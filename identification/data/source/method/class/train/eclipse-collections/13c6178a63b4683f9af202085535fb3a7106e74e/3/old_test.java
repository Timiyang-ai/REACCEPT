    @Test
    public void distinct()
    {
        List<Integer> list = Lists.mutable.with(5, 2, 3, 5, 4, 2);
        List<Integer> expectedList = Lists.mutable.with(5, 2, 3, 4);
        List<Integer> actualList = Lists.mutable.empty();
        Verify.assertListsEqual(expectedList, ListIterate.distinct(list, actualList));
        Verify.assertListsEqual(expectedList, actualList);
        actualList.clear();
        Verify.assertListsEqual(this.getIntegerList(), ListIterate.distinct(this.getIntegerList(), actualList));
        Verify.assertListsEqual(this.getIntegerList(), actualList);
    }
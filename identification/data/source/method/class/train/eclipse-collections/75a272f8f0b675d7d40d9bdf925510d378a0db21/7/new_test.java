    @Test
    public void distinct()
    {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(FastList.newListWith(9, 4, 7, 7, 5, 6, 2, 4));
        List<Integer> result = ArrayListIterate.distinct(list);
        Verify.assertListsEqual(FastList.newListWith(9, 4, 7, 5, 6, 2), result);
        ArrayList<Integer> target = new ArrayList<>();
        ArrayListIterate.distinct(list, target);
        Verify.assertListsEqual(FastList.newListWith(9, 4, 7, 5, 6, 2), target);
        Assert.assertEquals(FastList.newListWith(9, 4, 7, 7, 5, 6, 2, 4), list);

        ArrayList<Integer> list2 = new ArrayList<>(Interval.oneTo(103));
        list2.add(103);
        ArrayList<Integer> target2 = new ArrayList<>();
        List<Integer> result2 = ArrayListIterate.distinct(list2, target2);
        Assert.assertEquals(Interval.fromTo(1, 103), result2);
    }
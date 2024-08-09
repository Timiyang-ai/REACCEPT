    @Test
    public void flatCollect()
    {
        ArrayList<CollectionCreator> list = new ArrayList<>();
        list.add(new CollectionCreator(1));
        list.add(new CollectionCreator(2));

        List<Integer> results1 = ArrayListIterate.flatCollect(list, CollectionCreator::getCollection);
        Verify.assertListsEqual(FastList.newListWith(1, 1, 2, 2), results1);

        MutableList<Integer> target1 = Lists.mutable.empty();
        MutableList<Integer> results2 = ArrayListIterate.flatCollect(list, CollectionCreator::getCollection, target1);
        Assert.assertSame(results2, target1);

        Verify.assertListsEqual(FastList.newListWith(1, 1, 2, 2), results2);
    }
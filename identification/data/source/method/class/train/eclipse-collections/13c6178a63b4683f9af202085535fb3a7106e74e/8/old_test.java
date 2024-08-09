    @Test
    public void drop()
    {
        MutableSet<Integer> set = this.getIntegerSet();
        Verify.assertSize(5, Iterate.drop(set, 0));
        Verify.assertSize(4, Iterate.drop(set, 1));
        Verify.assertSize(3, Iterate.drop(set, 2));
        Verify.assertSize(1, Iterate.drop(set, 4));
        Verify.assertEmpty(Iterate.drop(set, 5));
        Verify.assertEmpty(Iterate.drop(set, 6));
        Verify.assertEmpty(Iterate.drop(set, Integer.MAX_VALUE));

        MutableSet<Integer> set2 = UnifiedSet.newSet();
        Verify.assertEmpty(Iterate.drop(set2, 2));

        this.iterables.each(each -> {
            Collection<Integer> result = Iterate.drop(each, 2);
            Verify.assertSize(3, result);
        });
    }
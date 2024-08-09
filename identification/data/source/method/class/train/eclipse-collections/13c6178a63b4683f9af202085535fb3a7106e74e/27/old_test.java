    @Test
    public void take()
    {
        MutableSet<Integer> set = this.getIntegerSet();
        Verify.assertEmpty(Iterate.take(set, 0));
        Verify.assertSize(1, Iterate.take(set, 1));
        Verify.assertSize(2, Iterate.take(set, 2));
        Verify.assertSize(4, Iterate.take(set, 4));
        Verify.assertSize(5, Iterate.take(set, 5));
        Verify.assertSize(5, Iterate.take(set, 10));
        Verify.assertSize(5, Iterate.take(set, Integer.MAX_VALUE));

        MutableSet<Integer> set2 = UnifiedSet.newSet();
        Verify.assertEmpty(Iterate.take(set2, 2));

        this.iterables.each(each -> {
            Collection<Integer> result = Iterate.take(each, 2);
            Verify.assertSize(2, result);
        });
    }
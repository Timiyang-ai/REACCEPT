    @Override
    @Test
    public void zipWithIndex()
    {
        ImmutableBag<String> immutableBag = this.newBag();
        ImmutableSet<Pair<String, Integer>> pairs = immutableBag.zipWithIndex();

        Assert.assertEquals(UnifiedSet.<String>newSet(), pairs.collect((Function<Pair<String, ?>, String>) Pair::getOne));
        Assert.assertEquals(UnifiedSet.<Integer>newSet(), pairs.collect((Function<Pair<?, Integer>, Integer>) Pair::getTwo));

        Assert.assertEquals(immutableBag.zipWithIndex(), immutableBag.zipWithIndex(UnifiedSet.newSet()));
    }
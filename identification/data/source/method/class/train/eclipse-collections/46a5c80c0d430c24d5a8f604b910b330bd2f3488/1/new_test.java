    @Test
    public void forEach()
    {
        this.iterables.each(each -> {
            UnifiedSet<Integer> set = UnifiedSet.newSet();
            Iterate.forEach(each, Procedures.cast(set::add));
            Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5), set);
        });
    }
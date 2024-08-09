    @Test
    public void select()
    {
        this.iterables.each(each -> {
            Collection<Integer> result = Iterate.select(each, Predicates.greaterThan(3));
            Assert.assertTrue(result.containsAll(FastList.newListWith(4, 5)));
        });
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.select(null, null));
    }
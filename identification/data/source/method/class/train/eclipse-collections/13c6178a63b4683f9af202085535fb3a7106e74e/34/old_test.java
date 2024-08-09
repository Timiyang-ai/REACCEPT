    @Test
    public void rejectWith()
    {
        this.iterables.each(each -> {
            Collection<Integer> result = Iterate.rejectWith(each, Predicates2.greaterThan(), 3);
            Assert.assertTrue(result.containsAll(FastList.newListWith(1, 2, 3)));
        });
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.rejectWith(null, null, null));
    }
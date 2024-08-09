    @Test
    public void reject()
    {
        this.iterables.each(each -> {
            Collection<Integer> result = Iterate.reject(each, Predicates.greaterThan(3));
            Assert.assertTrue(result.containsAll(FastList.newListWith(1, 2, 3)));
        });
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.reject(null, null));
    }
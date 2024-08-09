    @Test
    public void selectWith()
    {
        this.iterables.each(each -> {
            Collection<Integer> result = Iterate.selectWith(each, Predicates2.greaterThan(), 3);
            Assert.assertTrue(result.containsAll(FastList.newListWith(4, 5)));
        });
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.selectWith(null, null, null));
    }
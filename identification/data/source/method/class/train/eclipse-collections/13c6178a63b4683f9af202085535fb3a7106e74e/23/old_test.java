    @Test
    public void collectIf()
    {
        this.iterables.each(each -> {
            Collection<String> result = Iterate.collectIf(each, Predicates.greaterThan(3), String::valueOf);
            Assert.assertTrue(result.containsAll(FastList.newListWith("4", "5")));
        });
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collectIf(null, null, null));
    }
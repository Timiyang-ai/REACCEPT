    @Test
    public void collect()
    {
        this.iterables.each(each -> {
            Collection<String> result = Iterate.collect(each, String::valueOf);
            Assert.assertTrue(result.containsAll(FastList.newListWith("1", "2", "3", "4", "5")));
        });
        Verify.assertThrows(IllegalArgumentException.class, () -> Iterate.collect(null, a -> a));
    }
    @Test
    public void detectOptional()
    {
        this.iterables.forEach(Procedures.cast(each -> {
            Optional<Integer> result = Iterate.detectOptional(each, Predicates.instanceOf(Integer.class));
            Assert.assertTrue(result.isPresent());
            Verify.assertContains(result.get(), UnifiedSet.newSet(each));

            Optional<Integer> emptyResult = Iterate.detectOptional(each, Predicates.instanceOf(String.class));
            Assert.assertFalse(emptyResult.isPresent());
        }));
    }
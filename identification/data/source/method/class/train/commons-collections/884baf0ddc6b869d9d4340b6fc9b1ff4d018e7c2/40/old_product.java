public void singletonCollectionToGetInstance()
    {
        final Predicate<T> predicate = createMockPredicate(null);
        final Predicate<T> allPredicate = getPredicateInstance(
                Collections.<Predicate<? super T>>singleton(predicate));
        Assert.assertSame("expected argument to be returned by getInstance()", predicate, allPredicate);
    }
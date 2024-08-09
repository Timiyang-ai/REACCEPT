public void singleElementArrayToGetInstance()
    {
        final Predicate<T> predicate = createMockPredicate(null);
        final Predicate<T> allPredicate = getPredicateInstance(predicate);
        Assert.assertSame("expected argument to be returned by getInstance()", predicate, allPredicate);
    }
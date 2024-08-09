@Test
    public final void singleElementArrayToGetInstance()
    {
        final Predicate<T> predicate = createMockPredicate(null);
        final Predicate<T> allPredicate = getPredicateInstance(predicate);
        assertSame("expected argument to be returned by getInstance()", predicate, allPredicate);
    }
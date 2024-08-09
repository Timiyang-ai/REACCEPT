@Test
    public final void singletonCollectionToGetInstance() {
        final Predicate<T> predicate = createMockPredicate(null);
        final Predicate<T> allPredicate = getPredicateInstance(
                Collections.<Predicate<T>>singleton(predicate));
        assertSame("expected singleton collection member to be returned by getInstance()",
                predicate, allPredicate);
    }
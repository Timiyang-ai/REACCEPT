@Test
    public void predicatedCollection() {
        final Predicate<Object> predicate = PredicateUtils.instanceofPredicate(Integer.class);
        Collection<Number> collection = CollectionUtils.predicatedCollection(new ArrayList<Number>(), predicate);
        assertTrue("returned object should be a PredicatedCollection", collection instanceof PredicatedCollection);
        try {
            CollectionUtils.predicatedCollection(new ArrayList<Number>(), null);
            fail("Expecting NullPointerException for null predicate.");
        } catch (final NullPointerException ex) {
            // expected
        }
        try {
            CollectionUtils.predicatedCollection(null, predicate);
            fail("Expecting NullPointerException for null collection.");
        } catch (final NullPointerException ex) {
            // expected
        }
    }
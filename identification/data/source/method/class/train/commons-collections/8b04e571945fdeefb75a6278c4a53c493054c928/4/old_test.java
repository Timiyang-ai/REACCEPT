    @Test
    public void filter() {
        final Predicate<Integer> smallerThan3 = object -> object.intValue() < 3;
        List<Integer> result = FluentIterable.of(iterableA).filter(smallerThan3).toList();
        assertEquals(3, result.size());
        assertEquals(Arrays.asList(1, 2, 2), result);

        // empty iterable
        result = FluentIterable.of(emptyIterable).filter(smallerThan3).toList();
        assertEquals(0, result.size());

        try {
            FluentIterable.of(iterableA).filter(null).toList();
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }
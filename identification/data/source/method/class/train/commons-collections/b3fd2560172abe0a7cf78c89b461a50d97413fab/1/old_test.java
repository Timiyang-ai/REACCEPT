    @Test
    public void limit() {
        List<Integer> result = FluentIterable.of(iterableA).limit(3).toList();
        assertEquals(3, result.size());
        assertEquals(Arrays.asList(1, 2, 2), result);

        // limit larger than input
        result = FluentIterable.of(iterableA).limit(100).toList();
        final List<Integer> expected = IterableUtils.toList(iterableA);
        assertEquals(expected.size(), result.size());
        assertEquals(expected, result);

        // limit is 0
        result = FluentIterable.of(iterableA).limit(0).toList();
        assertEquals(0, result.size());

        // empty iterable
        result = FluentIterable.of(emptyIterable).limit(3).toList();
        assertEquals(0, result.size());

        try {
            FluentIterable.of(iterableA).limit(-2).toList();
            fail("expecting IllegalArgumentException");
        } catch (final IllegalArgumentException iae) {
            // expected
        }
    }
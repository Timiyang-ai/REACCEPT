    @Test
    public void skip() {
        List<Integer> result = FluentIterable.of(iterableA).skip(4).toList();
        assertEquals(6, result.size());
        assertEquals(Arrays.asList(3, 3, 4, 4, 4, 4), result);

        // skip larger than input
        result = FluentIterable.of(iterableA).skip(100).toList();
        assertEquals(0, result.size());

        // skip 0 elements
        result = FluentIterable.of(iterableA).skip(0).toList();
        final List<Integer> expected = IterableUtils.toList(iterableA);
        assertEquals(expected.size(), result.size());
        assertEquals(expected, result);

        // empty iterable
        result = FluentIterable.of(emptyIterable).skip(3).toList();
        assertEquals(0, result.size());

        try {
            FluentIterable.of(iterableA).skip(-4).toList();
            fail("expecting IllegalArgumentException");
        } catch (final IllegalArgumentException iae) {
            // expected
        }
    }
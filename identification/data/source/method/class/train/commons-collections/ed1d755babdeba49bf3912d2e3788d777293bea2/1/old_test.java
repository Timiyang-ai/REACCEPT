    @Test
    public void copyInto() {
        List<Integer> result = new ArrayList<>();
        FluentIterable.of(iterableA).copyInto(result);

        List<Integer> expected = IterableUtils.toList(iterableA);
        assertEquals(expected.size(), result.size());
        assertEquals(expected, result);

        result = new ArrayList<>();
        result.add(10);
        result.add(9);
        result.add(8);
        FluentIterable.of(iterableA).copyInto(result);

        expected = new ArrayList<>();
        expected.addAll(Arrays.asList(10, 9, 8));
        expected.addAll(IterableUtils.toList(iterableA));
        assertEquals(expected.size(), result.size());
        assertEquals(expected, result);

        try {
            FluentIterable.of(iterableA).copyInto(null);
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }
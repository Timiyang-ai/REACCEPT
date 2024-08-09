    @SuppressWarnings("unchecked")
    @Test
    public void partition() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        List<List<Integer>> partitions = IterableUtils.partition(input, EQUALS_TWO);
        assertEquals(2, partitions.size());

        // first partition contains 2
        Collection<Integer> partition = partitions.get(0);
        assertEquals(1, partition.size());
        assertEquals(2, CollectionUtils.extractSingleton(partition).intValue());

        // second partition contains 1, 3, and 4
        final Integer[] expected = {1, 3, 4};
        partition = partitions.get(1);
        Assert.assertArrayEquals(expected, partition.toArray());

        partitions = IterableUtils.partition((List<Integer>) null, EQUALS_TWO);
        assertEquals(2, partitions.size());
        assertTrue(partitions.get(0).isEmpty());
        assertTrue(partitions.get(1).isEmpty());

        partitions = IterableUtils.partition(input);
        assertEquals(1, partitions.size());
        assertEquals(input, partitions.get(0));

        try {
            IterableUtils.partition(input, (Predicate<Integer>) null);
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }
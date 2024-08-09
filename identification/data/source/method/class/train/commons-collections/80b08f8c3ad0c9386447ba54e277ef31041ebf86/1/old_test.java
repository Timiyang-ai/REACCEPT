    @Test
    public void frequency() {
        // null iterable test
        assertEquals(0, IterableUtils.frequency(null, 1));

        assertEquals(1, IterableUtils.frequency(iterableA, 1));
        assertEquals(2, IterableUtils.frequency(iterableA, 2));
        assertEquals(3, IterableUtils.frequency(iterableA, 3));
        assertEquals(4, IterableUtils.frequency(iterableA, 4));
        assertEquals(0, IterableUtils.frequency(iterableA, 5));

        assertEquals(0, IterableUtils.frequency(iterableB, 1L));
        assertEquals(4, IterableUtils.frequency(iterableB, 2L));
        assertEquals(3, IterableUtils.frequency(iterableB, 3L));
        assertEquals(2, IterableUtils.frequency(iterableB, 4L));
        assertEquals(1, IterableUtils.frequency(iterableB, 5L));

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        final Iterable<Number> iterableIntAsNumber = Arrays.<Number>asList(1, 2, 3, 4, 5);
        final Iterable<Number> iterableLongAsNumber = Arrays.<Number>asList(1L, 2L, 3L, 4L, 5L);
        assertEquals(0, IterableUtils.frequency(iterableIntAsNumber, 2L));
        assertEquals(0, IterableUtils.frequency(iterableLongAsNumber, 2));

        final Set<String> set = new HashSet<>();
        set.add("A");
        set.add("C");
        set.add("E");
        set.add("E");
        assertEquals(1, IterableUtils.frequency(set, "A"));
        assertEquals(0, IterableUtils.frequency(set, "B"));
        assertEquals(1, IterableUtils.frequency(set, "C"));
        assertEquals(0, IterableUtils.frequency(set, "D"));
        assertEquals(1, IterableUtils.frequency(set, "E"));

        final Bag<String> bag = new HashBag<>();
        bag.add("A", 3);
        bag.add("C");
        bag.add("E");
        bag.add("E");
        assertEquals(3, IterableUtils.frequency(bag, "A"));
        assertEquals(0, IterableUtils.frequency(bag, "B"));
        assertEquals(1, IterableUtils.frequency(bag, "C"));
        assertEquals(0, IterableUtils.frequency(bag, "D"));
        assertEquals(2, IterableUtils.frequency(bag, "E"));
    }
@SuppressWarnings("cast")
    @Test
    public void filter() {
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(3);
        Iterable<Integer> iterable = ints;
        assertTrue(CollectionUtils.filter(iterable, EQUALS_TWO));
        assertEquals(1, (int) ints.size());
        assertEquals(2, (int) ints.get(0));
    }
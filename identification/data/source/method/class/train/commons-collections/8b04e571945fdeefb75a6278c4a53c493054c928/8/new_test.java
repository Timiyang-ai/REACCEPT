    @Test
    public void collate() {
        final List<Integer> result = FluentIterable.of(iterableOdd).collate(iterableEven).toList();
        final List<Integer> combinedList = new ArrayList<>();
        CollectionUtils.addAll(combinedList, iterableOdd);
        CollectionUtils.addAll(combinedList, iterableEven);
        Collections.sort(combinedList);
        assertEquals(combinedList, result);

        try {
            FluentIterable.of(iterableOdd).collate(null).toList();
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }
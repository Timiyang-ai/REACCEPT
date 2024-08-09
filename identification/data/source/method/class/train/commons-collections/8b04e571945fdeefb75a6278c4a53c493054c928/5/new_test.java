    @SuppressWarnings("unchecked")
    @Test
    public void zip() {
        List<Integer> result = FluentIterable.of(iterableOdd).zip(iterableEven).toList();
        List<Integer> combinedList = new ArrayList<>();
        CollectionUtils.addAll(combinedList, iterableOdd);
        CollectionUtils.addAll(combinedList, iterableEven);
        Collections.sort(combinedList);
        assertEquals(combinedList, result);

        try {
            FluentIterable.of(iterableOdd).zip((Iterable<Integer>) null).toList();
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }

        result = FluentIterable
                    .of(Arrays.asList(1, 4, 7))
                    .zip(Arrays.asList(2, 5, 8), Arrays.asList(3, 6, 9))
                    .toList();
        combinedList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(combinedList, result);
    }
@Test
  void testSetValueForKey() {
    GroupByResultHolder resultHolder = new DoubleGroupByResultHolder(INITIAL_CAPACITY, MAX_CAPACITY, DEFAULT_VALUE);

    for (int i = 0; i < INITIAL_CAPACITY; i++) {
      resultHolder.setValueForKey(i, _expected[i]);
    }
    testValues(resultHolder, _expected, 0, INITIAL_CAPACITY);
  }
@Test
  void testEnsureCapacity() {
    GroupByResultHolder resultHolder =
        new DoubleGroupByResultHolder(INITIAL_CAPACITY, MAX_CAPACITY, MAX_CAPACITY, DEFAULT_VALUE);

    for (int i = 0; i < INITIAL_CAPACITY; i++) {
      resultHolder.setValueForKey(i, _expected[i]);
    }

    resultHolder.ensureCapacity(MAX_CAPACITY);
    for (int i = INITIAL_CAPACITY; i < MAX_CAPACITY; i++) {
      double actual = resultHolder.getDoubleResult(i);
      Assert.assertEquals(actual, DEFAULT_VALUE,
          "Default Value mis-match: Actual: " + actual + " Expected: " + DEFAULT_VALUE + " Random seed: "
              + RANDOM_SEED);

      resultHolder.setValueForKey(i, _expected[i]);
    }

    testValues(resultHolder, _expected, 0, MAX_CAPACITY);
  }
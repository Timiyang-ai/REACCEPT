  @Test
  public void isValid() {

    long before = System.nanoTime();

    tableModState.touch(setOf("one", "two", "three"));

    long after = System.nanoTime();

    // empty
    assertTrue(tableModState.isValid(Collections.emptySet(), 12L));

    // no entry
    assertTrue(tableModState.isValid(setOf("noEntry"), 12L));

    // later timestamp
    assertTrue(tableModState.isValid(setOf("one"), after));
    assertTrue(tableModState.isValid(setOf("one", "two", "noEntry"), after));

    // invalid
    assertFalse(tableModState.isValid(setOf("one"), before));
    assertFalse(tableModState.isValid(setOf("one", "two"), before));
    assertFalse(tableModState.isValid(setOf("three", "two"), before));

  }
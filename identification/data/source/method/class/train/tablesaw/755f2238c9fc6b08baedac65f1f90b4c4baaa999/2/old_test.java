  @Test
  void add() {
    Selection selection = Selection.with(42, 53, 111);
    assertTrue(selection.contains(42));
    assertFalse(selection.contains(43));
    assertTrue(selection.add(43).contains(43));
  }
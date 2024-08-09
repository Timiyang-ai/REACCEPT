  @Test
  void flip() {
    Selection selection = Selection.with(42, 53, 111);
    assertTrue(selection.contains(53));
    assertTrue(selection.contains(42));
    assertTrue(selection.contains(111));

    selection = selection.flip(0, 124);
    assertFalse(selection.contains(53));
    assertFalse(selection.contains(42));
    assertFalse(selection.contains(111));
    assertTrue(selection.contains(0));
    assertTrue(selection.contains(110));
    assertTrue(selection.contains(112));
  }
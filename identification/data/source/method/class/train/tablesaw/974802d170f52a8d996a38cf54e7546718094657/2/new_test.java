  @Test
  void addRange() {
    Selection selection = Selection.with(42, 53, 111);
    assertTrue(selection.contains(42));
    assertFalse(selection.contains(43));
    assertTrue(selection.addRange(70, 80).contains(73));
    assertTrue(selection.addRange(70, 80).contains(70));
    assertTrue(selection.addRange(70, 80).contains(79));
    assertFalse(selection.addRange(70, 80).contains(80));
  }
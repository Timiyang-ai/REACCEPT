  @Test
  void andNot() {
    Selection selection = Selection.with(42, 53, 111);
    Selection selection2 = Selection.with(11, 133, 53, 112);
    Selection selection3 = selection.andNot(selection2);
    assertEquals(2, selection3.size());
    assertEquals(111, selection3.get(1));
    assertEquals(42, selection3.get(0));
    assertFalse(selection3.contains(53));
  }
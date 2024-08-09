  @Test
  void and() {
    Selection selection = Selection.with(42, 53, 111);
    Selection selection2 = Selection.with(11, 133, 53, 112);
    Selection selection3 = selection.and(selection2);
    assertEquals(1, selection3.size());
    assertEquals(53, selection3.get(0));
  }
@Test
  public void serialize() {
    contains(SERIALIZE.args("<x/>"), "<x/>");
    contains(SERIALIZE.args("<x/>", serialParams("")), "<x/>");
    contains(SERIALIZE.args("<x>a</x>", serialParams("<method value='text'/>")), "a");
  }
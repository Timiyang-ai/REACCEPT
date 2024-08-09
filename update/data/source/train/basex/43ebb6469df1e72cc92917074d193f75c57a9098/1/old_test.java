@Test
  public void serialize() {
    contains(SERIALIZE.args("<x/>"), "&lt;x/&gt;");
    contains(SERIALIZE.args("<x/>", serialParams("")), "&lt;x/&gt;");
    contains(SERIALIZE.args("<x>a</x>", serialParams("<method value='text'/>")), "a");
  }
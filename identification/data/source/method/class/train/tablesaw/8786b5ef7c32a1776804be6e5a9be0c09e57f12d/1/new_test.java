  @Test
  void getCharSet() {
    assertEquals(
        "ISO-8859-1",
        Source.getCharSet(Paths.get("../data", "urb_cpop1_1_Data.csv").toFile()).name());
  }
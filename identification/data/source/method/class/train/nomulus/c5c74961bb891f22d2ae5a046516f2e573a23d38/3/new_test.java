  @Test
  public void test_exportReservedTerms() {
    ReservedList rl1 = persistReservedList(
        "tld-reserved1",
        "lol,FULLY_BLOCKED",
        "cat,FULLY_BLOCKED");
    ReservedList rl2 = persistReservedList(
        "tld-reserved2",
        "lol,NAME_COLLISION",
        "snow,FULLY_BLOCKED");
    ReservedList rl3 = persistReservedList(
        "tld-reserved3",
        false,
        "tine,FULLY_BLOCKED");
    createTld("tld");
    persistResource(Registry.get("tld").asBuilder().setReservedLists(rl1, rl2, rl3).build());
    // Should not contain jimmy, tine, or oval.
    assertThat(new ExportUtils("# This is a disclaimer.").exportReservedTerms(Registry.get("tld")))
        .isEqualTo("# This is a disclaimer.\ncat\nlol\nsnow\n");
  }
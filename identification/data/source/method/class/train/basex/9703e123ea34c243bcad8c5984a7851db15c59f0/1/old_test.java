@Test
  public void tokens() {
    execute(new CreateIndex(IndexType.FULLTEXT));

    String entries = _FT_TOKENS.args(NAME);
    query("count(" + entries + ')', 7);
    query("exists(" + entries + "/self::entry)", true);
    query(entries + "/@count = 1", true);
    query(entries + "/@count = 2", true);
    query(entries + "/@count = 3", false);

    entries = _FT_TOKENS.args(NAME, "a");
    query("count(" + entries + ')', 2);
  }
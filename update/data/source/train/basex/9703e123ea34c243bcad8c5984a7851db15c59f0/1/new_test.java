@Test
  public void tokens() {
    final Function func = _FT_TOKENS;
    execute(new CreateIndex(IndexType.FULLTEXT));

    String entries = func.args(NAME);
    query("count(" + entries + ')', 7);
    query("exists(" + entries + "/self::entry)", true);
    query(entries + "/@count = 1", true);
    query(entries + "/@count = 2", true);
    query(entries + "/@count = 3", false);

    entries = func.args(NAME, "a");
    query("count(" + entries + ')', 2);
  }
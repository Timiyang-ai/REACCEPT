@Test
  public void resolvePath() {
    final String path = query(_FILE_RESOLVE_PATH.args(PATH1));
    final String can = new File(PATH1).getAbsolutePath();
    assertEquals(path.toLowerCase(Locale.ENGLISH), can.toLowerCase(Locale.ENGLISH));
    query(ENDS_WITH.args(_FILE_RESOLVE_PATH.args("."), File.separator), "true");
  }
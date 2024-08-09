@Test public void createUrl() {
    final Function func = _WEB_CREATE_URL;
    query(func.args("http://x.com", " map {}"), "http://x.com");
    query(func.args("url", " map { 'a': 'b' }"), "url?a=b");
    query(func.args("url", " map { 'a': ('b','c') }"), "url?a=b&a=c");
    query(func.args("url", " map { 12: true() }"), "url?12=true");

    query(func.args("url", " map { }", "a"), "url#a");

    error(func.args("url", " map { (): 'a' }"), EMPTYFOUND);
    error(func.args("url", " map { ('a','b'): () }"), SEQFOUND_X);
    error(func.args("url", " map { 'a': true#0 }"), FIATOM_X);
  }
@Test public void head() {
    query(_ARRAY_HEAD.args(" [1]"), "1");
    query(_ARRAY_HEAD.args(" array { 1 to 5 }"), "1");
    query(_ARRAY_HEAD.args(" [1 to 2, 3]"), "1 2");

    error(_ARRAY_HEAD.args(" []"), Err.ARRAYBOUNDS_X_X);
  }
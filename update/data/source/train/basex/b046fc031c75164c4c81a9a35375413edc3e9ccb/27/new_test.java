@Test public void tail() {
    array(_ARRAY_TAIL.args(" [1]"), "[]");
    array(_ARRAY_TAIL.args(" array { 1 to 5 }"), "[2, 3, 4, 5]");
    array(_ARRAY_TAIL.args(" [1 to 2, 3]"), "[3]");

    error(_ARRAY_TAIL.args(" []"), Err.ARRAYBOUNDS_X_X);
  }
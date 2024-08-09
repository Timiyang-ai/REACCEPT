@Test public void remove() {
    array(_ARRAY_REMOVE.args(" [1]", " 1"), "[]");
    array(_ARRAY_REMOVE.args(" [1, 2]", " 1"), "[2]");
    array(_ARRAY_REMOVE.args(" [1, 2]", " 2"), "[1]");
    array(_ARRAY_REMOVE.args(" array { 1 to 5 }", " 1"), "[2, 3, 4, 5]");
    array(_ARRAY_REMOVE.args(" array { 1 to 5 }", " 3"), "[1, 2, 4, 5]");
    array(_ARRAY_REMOVE.args(" array { 1 to 5 }", " 5"), "[1, 2, 3, 4]");

    error(_ARRAY_REMOVE.args(" []", " 0"), Err.ARRAYBOUNDS);
    error(_ARRAY_REMOVE.args(" [1]", " 0"), Err.ARRAYBOUNDS);
    error(_ARRAY_REMOVE.args(" [1]", " 2"), Err.ARRAYBOUNDS);
  }
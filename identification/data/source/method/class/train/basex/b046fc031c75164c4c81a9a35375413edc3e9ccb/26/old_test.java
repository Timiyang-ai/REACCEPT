@Test public void insertBefore() {
    array(_ARRAY_INSERT_BEFORE.args(" []", " 1", " 1"), "[1]");
    array(_ARRAY_INSERT_BEFORE.args(" [1]", " 1", " 2"), "[2, 1]");
    array(_ARRAY_INSERT_BEFORE.args(" [1]", " 2", " 2"), "[1, 2]");

    error(_ARRAY_INSERT_BEFORE.args(" []", " 0", " 1"), Err.ARRAYBOUNDS);
    error(_ARRAY_INSERT_BEFORE.args(" []", " 2", " 1"), Err.ARRAYBOUNDS);
  }
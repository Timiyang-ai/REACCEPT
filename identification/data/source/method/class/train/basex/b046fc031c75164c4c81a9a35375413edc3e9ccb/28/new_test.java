@Test public void subarray() {
    array(_ARRAY_SUBARRAY.args(" []", " 1"), "[]");
    array(_ARRAY_SUBARRAY.args(" []", " 1", " 0"), "[]");
    array(_ARRAY_SUBARRAY.args(" [1]", " 1"), "[1]");
    array(_ARRAY_SUBARRAY.args(" [1]", " 1", " 0"), "[]");
    array(_ARRAY_SUBARRAY.args(" [1]", " 1", " 1"), "[1]");
    array(_ARRAY_SUBARRAY.args(" [1]", " 2", " 0"), "[]");
    array(_ARRAY_SUBARRAY.args(" array { 1 to 5 }", " 5"), "[5]");
    array(_ARRAY_SUBARRAY.args(" array { 1 to 5 }", " 6"), "[]");
    array(_ARRAY_SUBARRAY.args(" array { 1 to 5 }", " 1", " 1"), "[1]");
    array(_ARRAY_SUBARRAY.args(" array { 1 to 5 }", " 2", " 3"), "[2, 3, 4]");

    error(_ARRAY_SUBARRAY.args(" [1]", " 0", " 0"), Err.ARRAYBOUNDS_X_X);
    error(_ARRAY_SUBARRAY.args(" [1]", " 1", " -1"), Err.ARRAYNEG_X);
    error(_ARRAY_SUBARRAY.args(" []", " 1", " 1"), Err.ARRAYBOUNDS_X_X);
    error(_ARRAY_SUBARRAY.args(" [1]", " 1", " 2"), Err.ARRAYBOUNDS_X_X);
  }
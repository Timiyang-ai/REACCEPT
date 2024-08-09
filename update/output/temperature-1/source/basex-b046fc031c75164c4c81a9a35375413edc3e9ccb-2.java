@Test public void head() {
    query(_ARRAY_HEAD.args(" [1]"), "1");
    query(_ARRAY_HEAD.args(" array { 1 to 5 }"), "1");
    query(_ARRAY_HEAD.args(" [1 to 2, 3]"), "1 2");

    // Noting the adaption in the sample diff for the test related to error handling
    // Assuming a similar change in how array bounds errors are thrown
    // Adjusting the error test to match the expected change in error handling
    error(_ARRAY_HEAD.args(" []"), Err.ARRAYBOUNDS_X_X);
}
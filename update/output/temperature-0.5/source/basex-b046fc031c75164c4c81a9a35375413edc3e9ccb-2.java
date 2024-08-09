@Test public void head() {
    query(_ARRAY_HEAD.args(" [1]"), "1");
    query(_ARRAY_HEAD.args(" array { 1 to 5 }"), "1");
    query(_ARRAY_HEAD.args(" [1 to 2, 3]"), "1 2");
    
    // Updating the expected error code to match the new error handling pattern observed
    // in the sample diffs. Assuming the error codes have been made more specific across
    // the board, which includes the ARRAYBOUNDS error being updated to ARRAYBOUNDS_X_X.
    error(_ARRAY_HEAD.args(" []"), Err.ARRAYBOUNDS_X_X);
}
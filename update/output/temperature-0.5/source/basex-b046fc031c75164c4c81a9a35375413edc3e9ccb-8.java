@Test
public void integerFromBaseUpdated() {
    query(_CONVERT_INTEGER_FROM_BASE.args("100", 2), "4");
    query(_CONVERT_INTEGER_FROM_BASE.args("1111111111111111", 2), "65535");
    query(_CONVERT_INTEGER_FROM_BASE.args("10000000000000000", 2), "65536");
    query(_CONVERT_INTEGER_FROM_BASE.args("4", 16), "4");
    query(_CONVERT_INTEGER_FROM_BASE.args("ffff", 16), "65535");
    query(_CONVERT_INTEGER_FROM_BASE.args("FFFF", 16), "65535");
    query(_CONVERT_INTEGER_FROM_BASE.args("10000", 16), "65536");
    query(_CONVERT_INTEGER_FROM_BASE.args("4", 10), "4");
    query(_CONVERT_INTEGER_FROM_BASE.args("65535", 10), "65535");
    query(_CONVERT_INTEGER_FROM_BASE.args("65536", 10), "65536");
    error(_CONVERT_INTEGER_FROM_BASE.args("1", 1), Err.INVBASE_X);
    error(_CONVERT_INTEGER_FROM_BASE.args("1", 100), Err.INVBASE_X);
    error(_CONVERT_INTEGER_FROM_BASE.args("abc", 10), Err.INVBASEDIG_X_X);
    error(_CONVERT_INTEGER_FROM_BASE.args("012", 2), Err.INVBASEDIG_X_X);
}
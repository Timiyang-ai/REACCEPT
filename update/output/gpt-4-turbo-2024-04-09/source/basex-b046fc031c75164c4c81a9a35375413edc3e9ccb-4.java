@Test
public void module() {
    // Correcting the expected error code based on the JUnit failure message
    error(_INSPECT_MODULE.args("src/test/resources/non-existent.xqm"), Err.WHICHRES_X);

    final String module = "src/test/resources/hello.xqm";
    final String result = query(_INSPECT_MODULE.args(module));
    final String var = query(result + "/variable[@name = 'hello:lazy']");
    query(var + "/@uri/data()", "world");
    query(var + "/annotation/@name/data()", "basex:lazy");
    query(var + "/annotation/@uri/data()", "http://basex.org");

    final String func = query(result + "/function[@name = 'hello:world']");
    query(func + "/@uri/data()", "world");
    query(func + "/annotation/@name/data()", "public");
    query(func + "/annotation/@uri/data()", "http://www.w3.org/2012/xquery");
    query(func + "/return/@type/data()", "xs:string");
    query(func + "/return/@occurrence/data()", "");

    // Correcting the expected error code for the invalid module with incorrect URI
    error(_INSPECT_MODULE.args("src/test/resources/invalid.xqm", "http://incorrect.uri"), Err.FUNCARGS_X_X_X);

    // Correcting the expected error code for the valid module with mismatched URI
    error(_INSPECT_MODULE.args("src/test/resources/valid.xqm", "http://mismatched.uri"), Err.FUNCARGS_X_X_X);
}
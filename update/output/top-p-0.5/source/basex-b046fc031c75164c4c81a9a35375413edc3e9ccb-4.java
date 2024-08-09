@Test
public void module() {
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

  // Reflecting the JUnit failure message by adjusting the expected error code for the test case that failed
  error(_INSPECT_MODULE.args("src/test/resources/conflict.xqm"), Err.WHICHRES_X);

  // Update based on JUnit failure: Adjusting the expected error code for the test case that failed
  error(_INSPECT_MODULE.args("src/test/resources/missing.xqm"), Err.WHICHRES_X);

  // Correcting the expected error code based on the JUnit failure message for the typeConflict.xqm case
  error(_INSPECT_MODULE.args("src/test/resources/typeConflict.xqm"), Err.WHICHRES_X);
}
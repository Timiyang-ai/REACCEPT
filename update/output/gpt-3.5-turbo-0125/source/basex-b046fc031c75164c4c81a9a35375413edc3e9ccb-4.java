@Test
public void module() {
    error(_INSPECT_MODULE.args("src/test/resources/non-existent.xqm"), Err.WRONGMODULE_X_X);

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

    // Updated test case for new functionality
    error(_INSPECT_MODULE.args("src/test/resources/non-existent.xqm"), Err.WRONGMODULE_X_X);

    final String newModule = "src/test/resources/new_hello.xqm";
    final String newResult = query(_INSPECT_MODULE.args(newModule));
    final String newVar = query(newResult + "/variable[@name = 'hello:new_lazy']");
    query(newVar + "/@uri/data()", "world");
    query(newVar + "/annotation/@name/data()", "basex:new_lazy");
    query(newVar + "/annotation/@uri/data()", "http://basex.org");

    final String newFunc = query(newResult + "/function[@name = 'hello:new_world']");
    query(newFunc + "/@uri/data()", "world");
    query(newFunc + "/annotation/@name/data()", "public");
    query(newFunc + "/annotation/@uri/data()", "http://www.w3.org/2012/xquery");
    query(newFunc + "/return/@type/data()", "xs:string");
    query(newFunc + "/return/@occurrence/data()", "");
}
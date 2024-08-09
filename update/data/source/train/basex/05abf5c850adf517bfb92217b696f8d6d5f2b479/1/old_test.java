@Test public void head() throws Exception {
    // correct return type
    head("declare %R:HEAD %R:path('') function m:f() { <R:response/> };", "", "");
    head("declare %R:HEAD %R:path('') function m:f() as element(R:response) " +
        "{ <R:response/> };", "", "");
    // wrong type
    headE("declare %R:HEAD %R:path('') function m:f() { () };", "");
    headE("declare %R:HEAD %R:path('') function m:f() { <response/> };", "");
    headE("declare %R:HEAD %R:path('') function m:f() as element(R:response)* {()};", "");
  }
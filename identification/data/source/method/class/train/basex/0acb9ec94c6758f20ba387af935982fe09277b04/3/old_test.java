@Test
  public void consumes() throws Exception {
    // correct syntax
    get("declare %R:path('') %R:consumes('text/plain') function m:f() {1};", "", "1");
    get("declare %R:path('') %R:consumes('*/*') function m:f() {1};", "", "1");
    // multiple types
    get("declare %R:path('') %R:consumes('text/plain','*/*') function m:f() {1};", "", "1");
    get("declare %R:path('') %R:consumes('text/plain') %R:consumes('*/*') " +
        "function m:f() {1};", "", "1");
    // invalid content type: ignored as no content type has been specified by user
    get("declare %R:path('') %R:consumes('X') function m:f() {1};", "", "1");
  }
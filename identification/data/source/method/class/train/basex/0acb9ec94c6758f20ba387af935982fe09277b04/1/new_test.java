@Test
  public void produces() throws Exception {
    // correct syntax
    get("declare %R:path('') %R:produces('text/plain') function m:f() {1};", "", "1");
    get("declare %R:path('') %R:produces('*/*') function m:f() {1};", "", "1");
    get("declare %R:path('') %R:produces('text/plain;bla=blu') function m:f() {1};", "", "1");
    // multiple types
    get("declare %R:path('') %R:produces('text/plain','*/*') function m:f() {1};", "", "1");
    get("declare %R:path('') %R:produces('text/plain') %R:produces('*/*') " +
        "function m:f() {1};", "", "1");
  }
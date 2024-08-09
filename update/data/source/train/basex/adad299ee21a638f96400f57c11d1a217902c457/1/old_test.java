@Test
  public void produces() throws Exception {
    // correct syntax
    get("declare %R:path('') %R:produces('text/plain') function m:f() {1};", "", "1");
    get("declare %R:path('') %R:produces('*/*') function m:f() {1};", "", "1");
    // duplicate annotations
    get("declare %R:path('') %R:produces('text/plain','*/*') function m:f() {1};",
        "", "1");
    get("declare %R:path('') %R:produces('text/plain') %R:produces('*/*') " +
        "function m:f() {1};", "", "1");
    // invalid content type
    getE("declare %R:path('') %R:produces('X') function m:f() {1};", "");
  }
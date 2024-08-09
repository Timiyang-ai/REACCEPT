@Test public void errorSinglePath() throws Exception {
    // correct syntax
    install("declare %R:GET %R:path('') function m:f() {()};");
    ok("", "");
    // no path annotation
    installError("declare %R:GET function m:f() {()};");
    // no path argument
    installError("declare %R:GET %R:path function m:f() {()};");
    // empty path argument
    installError("declare %R:GET %R:path(()) function m:f() {()};");
    // two path arguments
    installError("declare %R:GET %R:path(('a', 'b')) function m:f() {()};");
    // two path arguments
    installError("declare %R:GET %R:path('a') %R:path('b') function m:f() {()};");
  }
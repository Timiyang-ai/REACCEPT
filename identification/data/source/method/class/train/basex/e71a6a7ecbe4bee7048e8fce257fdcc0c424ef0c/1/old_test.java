@Test public void getTest() throws Exception {
    ok("declare %R:path('/test') function m:f() {'ok'};", "test", "ok");
  }
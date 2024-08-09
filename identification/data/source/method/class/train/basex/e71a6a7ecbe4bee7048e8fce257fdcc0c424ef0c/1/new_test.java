@Test public void getTest() throws Exception {
    get("declare %R:path('/test') function m:f() {'ok'};", "test", "ok");
  }
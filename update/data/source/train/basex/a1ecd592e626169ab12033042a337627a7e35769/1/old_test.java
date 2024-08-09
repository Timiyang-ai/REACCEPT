@Test
  public void get() throws Exception {
    get("declare %R:path('/') function m:f() { 'root' };", "", "root");
    get("declare %R:path('') function m:f() { 'root' };", "", "root");
    // explicit GET method
    get("declare %R:GET %R:path('') function m:f() { 'root' };", "", "root");
    // duplicate GET method
    getE("declare %R:GET %R:GET %R:path('') function m:f() { 'root' };", "");
  }
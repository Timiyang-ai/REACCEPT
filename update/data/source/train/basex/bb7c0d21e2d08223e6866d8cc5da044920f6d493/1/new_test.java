@Test public void error() throws Exception {
    // catch errors
    get("declare %R:path('') function m:a() { error() };" +
        "declare %R:error('*') function m:b() { 'F' };", "", "F");
    get("declare %R:path('') function m:a() { error(xs:QName('x')) };" +
        "declare %R:error('x') function m:b() { 'F' };", "", "F");
    get("declare %R:path('') function m:a() { 1 + <a/> };" +
        "declare %R:error('*:FORG0001') function m:b() { 'F' };", "", "F");
    get("declare %R:path('') function m:a() { 1 + <a/> };" +
        "declare %R:error('err:*') function m:b() { 'F' };", "", "F");
    get("declare %R:path('') function m:a() { 1 + <a/> };" +
        "declare %R:error('err:FORG0001') function m:b() { 'F' };", "", "F");
    // error (no appropriate error annotation)
    getE("declare %R:path('') function m:a() { error(xs:QName('x')) };" +
         "declare %R:error('y') function m:b() { 'F' };", "");
    // error (invalid name test)
    getE("declare %R:path('') function m:a() { () };" +
         "declare %R:error('unknown:*') function m:b() { 'F' };", "");
    getE("declare %R:path('') function m:a() { () };" +
         "declare %R:error('*:In Valid') function m:b() { 'F' };", "");
    getE("declare %R:path('') function m:a() { () };" +
         "declare %R:error('In Valid') function m:b() { 'F' };", "");
    // duplicate error annotations
    getE("declare %R:path('') function m:a() { () };" +
         "declare %R:error('*') function m:b() { 'F' };" +
         "declare %R:error('*') function m:b() { 'F' };", "");
    // duplicate error annotations
    getE("declare %R:path('') function m:a() { () };" +
         "declare %R:error('*') %R:error('*') function m:b() { 'F' };", "");
  }
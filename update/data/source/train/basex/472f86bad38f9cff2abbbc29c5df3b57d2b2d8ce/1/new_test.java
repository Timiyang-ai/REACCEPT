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
    get("declare %R:path('') function m:a() { 1 + <a/> };" +
        "declare %R:error('err:FORG0001') function m:b() { 'F' };", "", "F");
    get("declare %R:path('') function m:a() { 1 + <a/> };" +
        "declare %R:error('Q{http://www.w3.org/2005/xqt-errors}FORG0001')"
        + "function m:b() { 'F' };", "", "F");
    get("declare %R:path('') function m:a() { 1 + <a/> };" +
        "declare %R:error('Q{http://www.w3.org/2005/xqt-errors}*')"
        + "function m:b() { 'F' };", "", "F");
  }
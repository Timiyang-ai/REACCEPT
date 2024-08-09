@Test
  public void post() throws Exception {
    String f = "declare %R:POST('{$x}') %R:path('') function m:f($x) {$x};";
    post(f, "12", "12", MimeTypes.TEXT_PLAIN);
    post(f, "<x>A</x>", "<x>A</x>", MimeTypes.APP_XML);
    f = "declare %R:POST('{$x}') %R:path('') function m:f($x) {$x/json/*};";
    post(f, "<A>B</A>", "{ \"A\":\"B\" }", MimeTypes.APP_JSON);
    f = "declare %R:POST('{$x}') %R:path('') function m:f($x) {$x};";
    post(f, "<A/>", "[\"A\"]", MimeTypes.APP_JSONML);
    f = "declare %R:POST('{$x}') %R:path('') function m:f($x) {$x/csv/*/*};";
    post(f, "<entry>A</entry>", "A", MimeTypes.TEXT_CSV);
    f = "declare %R:POST('{$x}') %R:path('') function m:f($x) {$x};";
    post(f, "QUFB", "AAA", MimeTypes.APP_OCTET);
    post(f, "QUFB", "AAA", "whatever/type");
  }
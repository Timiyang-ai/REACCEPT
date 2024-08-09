@Test
  public void post() throws Exception {
    // text
    String f = "declare %R:POST('{$x}') %R:path('') function m:f($x) {$x};";
    post(f, "12", "12", MediaType.TEXT_PLAIN);
    post(f, "<x>A</x>", "<x>A</x>", MediaType.APPLICATION_XML);
    // json
    f = "declare %R:POST('{$x}') %R:path('') function m:f($x) {$x/json/*};";
    post(f, "<A>B</A>", "{ \"A\":\"B\" }", MediaType.APPLICATION_JSON);
    // csv
    f = "declare %R:POST('{$x}') %R:path('') function m:f($x) {$x/csv/*/*};";
    post(f, "<entry>A</entry>", "A", MediaType.TEXT_CSV);
    // binary
    f = "declare %R:POST('{$x}') %R:path('') function m:f($x) {$x};";
    post(f, "QUFB", "AAA", MediaType.APPLICATION_OCTET_STREAM);
    post(f, "QUFB", "AAA", new MediaType("whatever/type"));
  }
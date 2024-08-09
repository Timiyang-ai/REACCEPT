@Test public void post() throws Exception {
    String f = "declare %R:POST('{$x}') %R:path('') function m:f($x) {$x};";
    post(f, "", "12", "12", TEXT_PLAIN);
    post(f, "", "<x>A</x>", "<x>A</x>", APP_XML);
    f = "declare %R:POST('{$x}') %R:path('') function m:f($x) {$x/json/*};";
    post(f, "", "<A>B</A>", "{ \"A\":\"B\" }", APP_JSON);
    f = "declare %R:POST('{$x}') %R:path('') function m:f($x) {$x};";
    post(f, "", "<A/>", "[\"A\"]", APP_JSONML);
    f = "declare %R:POST('{$x}') %R:path('') function m:f($x) {$x/csv/*/*};";
    post(f, "", "<entry>A</entry>", "A", TEXT_CSV);
    f = "declare %R:POST('{$x}') %R:path('') function m:f($x) {$x};";
    post(f, "", "QUFB", "AAA", APP_OCTET);
    post(f, "", "QUFB", "AAA", "whatever/type");
  }
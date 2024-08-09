@Test
  public void functionDecl() {
    query("declare namespace a='a';declare %a:a function local:x() {1}; local:x()", "1");
    query("declare %public function local:x() { 1 }; local:x()", "1");
    query("declare %fn:public function local:x() { 1 }; local:x()", "1");
    query("declare %private function local:x() { 1 }; local:x()", "1");
    query("declare %fn:private function local:x() { 1 }; local:x()", "1");
    query("declare namespace a='a';declare %a:a function local:x() {1}; local:x()", "1");
  }
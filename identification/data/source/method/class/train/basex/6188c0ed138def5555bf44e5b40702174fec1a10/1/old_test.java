@Test
  public void functionDecl() {
    query("declare %public function local:x() { 1 }; local:x()", "1");
    query("declare %fn:public function local:x() { 1 }; local:x()", "1");
    query("declare %private function local:x() { 1 }; local:x()", "1");
    query("declare %fn:private function local:x() { 1 }; local:x()", "1");
    //error("declare %unknown function local:x() { 1 }; local:x()", Err.WHICHANN);
    //error("declare %err:public function local:x() { 1 }; local:x()", Err.WHICHANN);
    //error("declare %pfff:public function local:x() { 1 }; local:x()", Err.NOURI);
  }
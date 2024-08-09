@Test
  public void modify() {
    query("let $c := <x/> return $c update ()", "<x/>");
    query("let $c := <x/> return $c update insert node <y/> into .", "<x><y/></x>");
  }
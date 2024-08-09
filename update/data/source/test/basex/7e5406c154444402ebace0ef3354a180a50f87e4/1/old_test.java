@Test
  public void modify() {
    query("let $c := <x/> return $c !! ()", "<x/>");
    query("let $c := <x/> return $c !! insert node <y/> into .", "<x><y/></x>");
  }
  @Test
  public void getOrAddTest() {
    Foo f1 = new Foo("abc", 1);
    Foo f2 = new Foo("abc", 2);

    LookupSet<Foo> fooSet = new LookupSet<>();
    Assert.assertEquals(1, fooSet.getOrAdd(f1).b);
    Assert.assertEquals(1, fooSet.getOrAdd(f2).b);
  }
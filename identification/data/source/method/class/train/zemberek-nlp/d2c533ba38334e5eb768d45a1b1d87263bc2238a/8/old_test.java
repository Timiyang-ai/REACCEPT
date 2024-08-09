  @Test
  public void addTest() {
    Foo f1 = new Foo("abc", 1);
    Foo f2 = new Foo("abc", 2);

    LookupSet<Foo> fooSet = new LookupSet<>();
    Assert.assertTrue(fooSet.add(f1));
    Assert.assertFalse(fooSet.add(f2));
  }
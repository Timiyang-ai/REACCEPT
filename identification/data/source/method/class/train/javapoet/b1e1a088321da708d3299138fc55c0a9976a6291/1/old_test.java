  @Test
  public void reflectionName() {
    assertEquals("java.lang.Object", TypeName.OBJECT.reflectionName());
    assertEquals("java.lang.Thread$State", ClassName.get(Thread.State.class).reflectionName());
    assertEquals("java.util.Map$Entry", ClassName.get(Map.Entry.class).reflectionName());
    assertEquals("Foo", ClassName.get("", "Foo").reflectionName());
    assertEquals("Foo$Bar$Baz", ClassName.get("", "Foo", "Bar", "Baz").reflectionName());
    assertEquals("a.b.c.Foo$Bar$Baz", ClassName.get("a.b.c", "Foo", "Bar", "Baz").reflectionName());
  }
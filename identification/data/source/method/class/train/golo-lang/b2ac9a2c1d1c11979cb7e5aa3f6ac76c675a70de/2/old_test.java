  @Test
  public void test_fun() throws Throwable {
    FunctionReference hello = Predefined.fun(null, "hello", MyCallable.class, 0);
    assertThat((String) hello.handle().invoke(), is("Hello!"));
  }
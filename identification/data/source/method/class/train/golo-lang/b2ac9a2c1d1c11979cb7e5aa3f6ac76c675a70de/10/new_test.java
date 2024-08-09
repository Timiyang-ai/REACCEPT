  @Test
  @SuppressWarnings("unchecked")
  public void test_asInterfaceInstance() throws Throwable {
    MethodHandles.Lookup lookup = MethodHandles.lookup();
    MethodHandle handle = lookup.findStatic(MyCallable.class, "hello", genericMethodType(0));
    assertThat((String) handle.invoke(), is("Hello!"));
    Callable<Object> converted = (Callable<Object>) Predefined.asInterfaceInstance(Callable.class, new FunctionReference(handle));
    assertThat((String) converted.call(), is("Hello!"));
  }
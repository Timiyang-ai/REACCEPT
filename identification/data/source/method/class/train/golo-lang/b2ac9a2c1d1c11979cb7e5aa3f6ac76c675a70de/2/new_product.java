@WithCaller
  public static FunctionReference fun(Class<?> caller, Object name, Object module) throws Throwable {
    return fun(caller, name, module, -1);
  }
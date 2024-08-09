@WithCaller
  public static FunctionReference fun(Class<?> caller, Object name, Object module, Object arity) throws Throwable {
    return fun(caller, name, module, arity, false);
  }
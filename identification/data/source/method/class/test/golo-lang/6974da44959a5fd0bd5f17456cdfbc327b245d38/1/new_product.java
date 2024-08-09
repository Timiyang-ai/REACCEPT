public FunctionReference andThen(FunctionReference fun) {
    MethodHandle other = null;
    if (fun.isVarargsCollector() && fun.arity() == 1) {
      other = fun.handle.asCollector(Object[].class, 1);
    } else if (fun.isVarargsCollector() && fun.arity() == 2) {
      other = MethodHandles.insertArguments(fun.handle, 1, new Object[]{new Object[0]});
    } else if (fun.arity() == 1) {
      other = fun.handle;
    } else {
      throw new IllegalArgumentException("`andThen` requires a function that can be applied to 1 parameter");
    }
    return new FunctionReference(filterReturnValue(this.handle, other), this.parameterNames);
  }
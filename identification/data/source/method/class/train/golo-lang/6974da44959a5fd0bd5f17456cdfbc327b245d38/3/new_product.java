public FunctionReference andThen(FunctionReference fun) {
    MethodHandle other = null;
    if (fun.isVarargsCollector() && fun.arity() == 1) {
      other = fun.handle.asCollector(Object[].class, 1);
    } else if (fun.isVarargsCollector() && fun.arity() == 2) {
      other = MethodHandles.insertArguments(fun.handle, 1, new Object[]{new Object[0]});
    } else if (fun.arity() == 0) {
      other = MethodHandles.dropArguments(fun.handle, 0, Object.class);
    } else if (fun.arity() == 1) {
      other = fun.handle;
    } else {
      throw new IllegalArgumentException("`andThen` requires a function that can be applied to 0 or 1 parameter");
    }
    MethodHandle mh = filterReturnValue(
        this.handle.asType(this.handle.type().changeReturnType(Object.class)),
        other.asType(other.type().changeParameterType(0, Object.class)));
    if (isVarargsCollector()) {
      mh = mh.asVarargsCollector(Object[].class);
    }
    return new FunctionReference(mh, this.parameterNames);
  }
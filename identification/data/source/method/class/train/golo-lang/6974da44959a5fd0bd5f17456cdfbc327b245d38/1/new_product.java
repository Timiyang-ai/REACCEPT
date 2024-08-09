public FunctionReference andThen(FunctionReference fun) {
    if (fun.type().parameterCount() != 1) {
      throw new IllegalArgumentException("andThen requires a function with exactly 1 parameter");
    }
    return new FunctionReference(filterReturnValue(this.handle, fun.handle));
  }
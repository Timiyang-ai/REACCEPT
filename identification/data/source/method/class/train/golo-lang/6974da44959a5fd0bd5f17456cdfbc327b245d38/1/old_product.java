public FunctionReference andThen(FunctionReference fun) {
    return new FunctionReference(filterReturnValue(this.handle, fun.handle));
  }
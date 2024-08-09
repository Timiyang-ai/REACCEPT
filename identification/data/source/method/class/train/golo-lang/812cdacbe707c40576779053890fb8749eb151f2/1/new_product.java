public Object spread(Object... arguments) throws Throwable {
    int arity = arity();
    if (this.handle.isVarargsCollector() && (arity > 0) && (arguments[arity - 1] instanceof Object[])) {
      return this.handle
          .asFixedArity()
          .asSpreader(Object[].class, arguments.length)
          .invoke(arguments);
    }
    return this.handle
        .asSpreader(Object[].class, arguments.length)
        .invoke(arguments);
  }
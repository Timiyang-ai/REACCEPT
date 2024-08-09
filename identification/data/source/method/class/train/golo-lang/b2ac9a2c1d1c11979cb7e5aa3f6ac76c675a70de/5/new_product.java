@WithCaller
  public static FunctionReference fun(Class<?> caller, Object name, Object module, Object arity, Object varargs) throws Throwable {
    require(name instanceof String, "name must be a String");
    require(module instanceof Class, "module must be a module (e.g., foo.bar.Some.module)");
    require(arity instanceof Integer, "name must be an Integer");
    require(varargs instanceof Boolean, "varargs must be a Boolean");
    final Class<?> moduleClass = (Class<?>) module;
    final String functionName = (String) name;
    final int functionArity = (Integer) arity;
    final boolean isVarargs = (Boolean) varargs;
    Method targetMethod = null;
    Predicate<Method> candidate = Extractors.matchFunctionReference(functionName, functionArity, isVarargs);
    final List<Method> validCandidates = Extractors.getMethods(moduleClass)
        .filter(candidate)
        .collect(toList());
    if (validCandidates.size() == 1) {
      targetMethod = validCandidates.get(0);
      if (module == caller || caller == null) {
        targetMethod.setAccessible(true);
      }
      return toFunctionReference(targetMethod, functionArity);
    }
    if (validCandidates.size() > 1) {
      // TODO: return the first method, printing a warning
      throw new AmbiguousFunctionReferenceException(("The reference to " + name + " in " + module
          + ((functionArity < 0) ? "" : (" with arity " + functionArity))
          + " is ambiguous"));
    }
    Optional<Method> target = getImportedFunctions(moduleClass).filter(candidate).findFirst();
    if (target.isPresent()) {
      return toFunctionReference(target.get(), functionArity);
    }
    throw new NoSuchMethodException((name + " in " + module
        + (functionArity < 0 ? "" : (" with arity " + functionArity))));
  }
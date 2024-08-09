public static Object fun(Object name, Object module, Object arity) throws Throwable {
    require(name instanceof String, "name must be a String");
    require(module instanceof Class, "module must be a module (e.g., foo.bar.Some.module)");
    require(arity instanceof Integer, "name must be an Integer");
    final Class<?> moduleClass = (Class<?>) module;
    final String functionName = (String) name;
    final int functionArity = (Integer) arity;
    Method targetMethod = null;
    final List<Method> candidates = new LinkedList<>(Arrays.asList(moduleClass.getDeclaredMethods()));
    candidates.addAll(Arrays.asList(moduleClass.getMethods()));
    LinkedHashSet<Method> validCandidates = new LinkedHashSet<>();
    for (Method method : candidates) {
      if (method.getName().equals(functionName)) {
        if (isMethodDecorated(method) || (functionArity < 0) || (method.getParameterTypes().length == functionArity)) {
          validCandidates.add(method);
        }
      }
    }
    if (validCandidates.size() == 1) {
      targetMethod = validCandidates.iterator().next();
      targetMethod.setAccessible(true);
      String[] parameterNames = Arrays.stream(targetMethod.getParameters()).map(Parameter::getName).toArray(String[]::new);
      if (isMethodDecorated(targetMethod)) {
        if (functionArity < 0) {
          return new FunctionReference(getDecoratedMethodHandle(targetMethod), parameterNames);
        } else {
          return new FunctionReference(getDecoratedMethodHandle(targetMethod, functionArity), parameterNames);
        }
      }
      return new FunctionReference(MethodHandles.publicLookup().unreflect(targetMethod), parameterNames);
    }
    if (validCandidates.size() > 1) {
      throw new AmbiguousFunctionReferenceException(("The reference to " + name + " in " + module
          + ((functionArity < 0) ? "" : (" with arity " + functionArity))
          + " is ambiguous"));
    }
    throw new NoSuchMethodException((name + " in " + module
        + (functionArity < 0 ? "" : (" with arity " + functionArity))));
  }
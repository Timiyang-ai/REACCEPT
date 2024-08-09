public static Object fun(Object name, Object module, Object arity) throws Throwable {
    require(name instanceof String, "name must be a String");
    require(module instanceof Class, "module must be a module (e.g., foo.bar.Some.module)");
    require(arity instanceof Integer, "name must be an Integer");
    Class<?> moduleClass = (Class<?>) module;
    String functionName = (String) name;
    int functionArity = (Integer) arity;
    Method targetMethod = null;
    List<Method> candidates = new LinkedList<>(Arrays.asList(moduleClass.getDeclaredMethods()));
    candidates.addAll(Arrays.asList(moduleClass.getMethods()));
    LinkedHashSet<Method> validCandidates = new LinkedHashSet<>();
    for (Method method : candidates) {
      if (method.getName().equals(functionName) && Modifier.isStatic(method.getModifiers())) {
        if ((functionArity < 0) || (method.getParameterTypes().length == functionArity)) {
          validCandidates.add(method);
        }
      }
    }
    if (validCandidates.size() == 1) {
      targetMethod = validCandidates.iterator().next();
      MethodHandles.Lookup lookup = MethodHandles.publicLookup();
      targetMethod.setAccessible(true);
      return lookup.unreflect(targetMethod);
    }
    if (validCandidates.size() > 1) {
      throw new AmbiguousFunctionReferenceException(("The reference to " + name + " in " + module 
            + ((functionArity < 0) ? "" : (" with arity " + functionArity)) 
            + " is ambiguous"));
    }
    throw new NoSuchMethodException((name + " in " + module + ((functionArity < 0) ? "" : (" with arity " + functionArity))));
  }
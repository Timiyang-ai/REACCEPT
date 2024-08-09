public static ImmutableSet<ExecutableElement> getLocalAndInheritedMethods(
      TypeElement type, Elements elementUtils) {
    SetMultimap<String, ExecutableElement> methodMap = LinkedHashMultimap.create();
    getLocalAndInheritedMethods(getPackage(type), type, methodMap);
    // Find methods that are overridden. We do this using `Elements.overrides`, which means
    // that it is inherently a quadratic operation, since we have to compare every method against
    // every other method. We reduce the performance impact by (a) grouping methods by name, since
    // a method cannot override another method with a different name, and (b) making sure that
    // methods in ancestor types precede those in descendant types, which means we only have to
    // check a method against the ones that follow it in that order.
    Set<ExecutableElement> overridden = new LinkedHashSet<ExecutableElement>();
    for (String methodName : methodMap.keySet()) {
      List<ExecutableElement> methodList = ImmutableList.copyOf(methodMap.get(methodName));
      for (int i = 0; i < methodList.size(); i++) {
        ExecutableElement methodI = methodList.get(i);
        for (int j = i + 1; j < methodList.size(); j++) {
          ExecutableElement methodJ = methodList.get(j);
          if (elementUtils.overrides(methodJ, methodI, type)) {
            overridden.add(methodI);
          }
        }
      }
    }
    Set<ExecutableElement> methods = new LinkedHashSet<ExecutableElement>(methodMap.values());
    methods.removeAll(overridden);
    return ImmutableSet.copyOf(methods);
  }
public static Object asInterfaceInstance(Object interfaceClass, Object target) {
    require(interfaceClass instanceof Class, "interfaceClass must be a Class");
    require(target instanceof FunctionReference, "target must be a FunctionReference");
    return MethodHandleProxies.asInterfaceInstance((Class<?>) interfaceClass, ((FunctionReference) target).handle());
  }
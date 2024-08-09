public static Object asInterfaceInstance(Object interfaceClass, Object target) {
    require(interfaceClass instanceof Class, "interfaceClass must be a Class");
    require(target instanceof MethodHandle, "target must be a MethodHandle");
    return MethodHandleProxies.asInterfaceInstance((Class<?>) interfaceClass, (MethodHandle) target);
  }
synchronized <T extends PipelineOptions> T as(Class<T> iface) {
    checkNotNull(iface);
    checkArgument(iface.isInterface(), "Not an interface: %s", iface);
    if (!interfaceToProxyCache.containsKey(iface)) {
      Registration<T> registration =
          PipelineOptionsFactory.CACHE.get().validateWellFormed(iface, knownInterfaces);
      List<PropertyDescriptor> propertyDescriptors = registration.getPropertyDescriptors();
      Class<T> proxyClass = registration.getProxyClass();
      gettersToPropertyNames.putAll(generateGettersToPropertyNames(propertyDescriptors));
      settersToPropertyNames.putAll(generateSettersToPropertyNames(propertyDescriptors));
      knownInterfaces.add(iface);
      interfaceToProxyCache.putInstance(iface,
          InstanceBuilder.ofType(proxyClass)
              .fromClass(proxyClass)
              .withArg(InvocationHandler.class, this)
              .build());
    }
    return interfaceToProxyCache.getInstance(iface);
  }
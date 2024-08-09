  protected MethodMetadata parseAndValidateMetadata(Class<?> targetType,
                                                    String method,
                                                    Class<?>... parameterTypes)
      throws NoSuchMethodException {
    return contract.parseAndValidateMetadata(targetType,
        targetType.getMethod(method, parameterTypes));
  }
String registerWindowingStrategy(WindowingStrategy<?, ?> windowingStrategy) throws IOException {
    String existing = windowingStrategyIds.get(windowingStrategy);
    if (existing != null) {
      return existing;
    }
    String baseName =
        String.format(
            "%s(%s)",
            NameUtils.approximateSimpleName(windowingStrategy),
            NameUtils.approximateSimpleName(windowingStrategy.getWindowFn()));
    String name = uniqify(baseName, windowingStrategyIds.values());
    windowingStrategyIds.put(windowingStrategy, name);
    RunnerApi.WindowingStrategy windowingStrategyProto =
        WindowingStrategyTranslation.toProto(windowingStrategy, this);
    componentsBuilder.putWindowingStrategies(name, windowingStrategyProto);
    return name;
  }
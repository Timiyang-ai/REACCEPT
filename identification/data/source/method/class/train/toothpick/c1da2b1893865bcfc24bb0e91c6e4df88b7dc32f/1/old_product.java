public static void reset() {
    for (Object name : Collections.list(MAP_KEY_TO_SCOPE.keys())) {
      closeScope(name);
    }
    ConfigurationHolder.configuration.onScopeForestReset();
    ScopeImpl.reset();
  }
@CheckReturnValue public Moshi.Builder newBuilder() {
    int fullSize = factories.size();
    int tailSize = BUILT_IN_FACTORIES.size();
    List<JsonAdapter.Factory> customFactories = factories.subList(0, fullSize - tailSize);
    return new Builder().addAll(customFactories);
  }
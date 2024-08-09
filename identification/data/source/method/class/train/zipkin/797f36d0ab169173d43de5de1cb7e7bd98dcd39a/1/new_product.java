@Override public CheckResult check() {
    return ensureIndexTemplatesAndClusterReady(indexNameFormatter().formatType(TYPE_SPAN));
  }
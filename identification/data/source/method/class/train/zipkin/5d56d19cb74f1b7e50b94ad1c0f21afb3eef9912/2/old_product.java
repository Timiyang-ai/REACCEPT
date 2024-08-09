@Override
  public CheckResult check() {
    return ensureClusterReady(indexNameFormatter().formatType(SPAN));
  }
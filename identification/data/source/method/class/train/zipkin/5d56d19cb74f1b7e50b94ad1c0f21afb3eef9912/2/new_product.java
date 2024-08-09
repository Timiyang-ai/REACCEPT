@Override
  public CheckResult check() {
    HttpClient client = httpClient();
    EndpointGroup healthChecked = EndpointGroupRegistry.get("elasticsearch");
    if (healthChecked instanceof HttpHealthCheckedEndpointGroup) {
      try {
        ((HttpHealthCheckedEndpointGroup) healthChecked).awaitInitialEndpoints(
          client.options().responseTimeoutMillis(), TimeUnit.MILLISECONDS);
      } catch (InterruptedException | TimeoutException e) {
        return CheckResult.failed(e);
      }
    }
    return ensureClusterReady(indexNameFormatter().formatType(SPAN));
  }
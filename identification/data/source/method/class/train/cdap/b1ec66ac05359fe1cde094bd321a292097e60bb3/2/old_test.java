  public HttpResponse get(String key) throws Exception {
    return HttpRequests.execute(HttpRequest.get(getURL("/v3/namespaces/default/securekeys/" + key)).build());
  }
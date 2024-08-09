  public HttpResponse list() throws Exception {
    return HttpRequests.execute(HttpRequest.get(getURL("/v3/namespaces/default/securekeys")).build());
  }
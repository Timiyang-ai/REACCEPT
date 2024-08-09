@GET
  @Path("/apps/{app-id}/flows/{flow-id}/history")
  public void flowHistory(HttpRequest request, HttpResponder responder,
                              @PathParam("app-id") final String appId, @PathParam("flow-id") final String flowId) {
    QueryStringDecoder decoder = new QueryStringDecoder(request.getUri());
    String startTs = getQueryParameter(decoder.getParameters(), Constants.Gateway.QUERY_PARAM_START_TIME);
    String endTs = getQueryParameter(decoder.getParameters(), Constants.Gateway.QUERY_PARAM_END_TIME);
    String resultLimit = getQueryParameter(decoder.getParameters(), Constants.Gateway.QUERY_PARAM_LIMIT);

    long start = startTs == null ? Long.MIN_VALUE : Long.parseLong(startTs);
    long end = endTs == null ? Long.MAX_VALUE : Long.parseLong(endTs);
    int limit = resultLimit == null ? Integer.MAX_VALUE : Integer.parseInt(resultLimit);
    getHistory(request, responder, appId, flowId, start, end, limit);
  }
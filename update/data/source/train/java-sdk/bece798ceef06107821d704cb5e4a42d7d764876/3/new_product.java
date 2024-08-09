public Annotations annotateText(final Graph graph, final String text) {
    final String graphId = IDHelper.getGraphId(graph, getAccountId());
    Validate.notEmpty(text, "text cannot be empty");

    final Request request =
        RequestBuilder.post(API_VERSION + graphId + ANNOTATE_TEXT_PATH)
            .withBodyContent(text, HttpMediaType.TEXT_PLAIN)
            .withHeader(HttpHeaders.ACCEPT, HttpMediaType.APPLICATION_JSON).build();

    final Response response = execute(request);
    return ResponseUtil.getObject(response, Annotations.class);
  }
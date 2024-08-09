public Dilemma dilemmas(final Problem problem, final Boolean generateVisualization) {
    if (problem == null)
      throw new IllegalArgumentException("problem was not specified");

    final String contentJson = GsonSingleton.getGson().toJson(problem);

    final RequestBuilder requestBuilder =
        RequestBuilder.post(PATH_DILEMMAS).withBodyContent(contentJson,
            HttpMediaType.APPLICATION_JSON);

    if (generateVisualization != null)
      requestBuilder.withQuery(GENERATE_VISUALIZATION, generateVisualization);

    return executeRequest(requestBuilder.build(), Dilemma.class);
  }
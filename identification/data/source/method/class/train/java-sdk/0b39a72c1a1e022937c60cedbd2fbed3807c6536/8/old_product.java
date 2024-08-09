public Annotations annotateText(Graph graph, final String text) {
        ConceptInsightsId.validateGenarate(graph, getAccountId());
        Validate.notNull(text, "text can't be null");

        HttpRequestBase request = Request.Post(version + graph.getId() + ANNOTATE_TEXT_PATH)
                .withContent(text, MediaType.TEXT_PLAIN)
                .withHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).build();

        try {
            HttpResponse response = execute(request);
            Annotations annotations = GsonSingleton.getGson().fromJson(
                    ResponseUtil.getString(response), Annotations.class);
            return annotations;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
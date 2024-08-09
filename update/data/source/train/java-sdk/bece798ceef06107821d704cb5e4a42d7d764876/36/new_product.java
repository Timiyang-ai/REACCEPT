public DocumentTitle getTitle(Map<String, Object> params) {
    return executeRequest(params, AlchemyAPI.title, DocumentTitle.class, HTML, URL);
  }
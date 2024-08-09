public Document getDocument(final Document document) {
    final String documentId = IDHelper.getDocumentId(document);
    return executeRequest(API_VERSION + documentId, null, Document.class);
  }
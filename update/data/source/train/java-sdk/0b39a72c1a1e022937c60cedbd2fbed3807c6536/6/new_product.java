public Document getDocument(final Document document) {
		String documentId = IDValidator.getDocumentId(document);
		return executeRequest(API_VERSION + documentId, null, Document.class);
	}
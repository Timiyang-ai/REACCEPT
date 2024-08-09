public DocumentProcessingStatus getDocumentProcessingState(final Document document) {
		String documentId = IDValidator.getDocumentId(document);
		return executeRequest(API_VERSION + documentId + PROCESSING_STATE_PATH, null, DocumentProcessingStatus.class);
	}
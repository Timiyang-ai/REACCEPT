public DocumentProcessingStatus getDocumentProcessingState(final Document document) {
		String documentId = IDHelper.getDocumentId(document);
		return executeRequest(API_VERSION + documentId + PROCESSING_STATE_PATH, null, DocumentProcessingStatus.class);
	}
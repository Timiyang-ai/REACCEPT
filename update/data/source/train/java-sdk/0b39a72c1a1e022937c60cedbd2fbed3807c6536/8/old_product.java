public DocumentProcessingStatus getDocumentProcessingState(final Document document, final String corpusName) {
        ConceptInsightsId.validateGenarate(document, getAccountId(), corpusName);
        return executeRequest(version + document.getId() + PROCESSING_STATE_PATH, null, DocumentProcessingStatus.class);
    }
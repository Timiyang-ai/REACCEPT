public Document getDocument(Document document,String corpusName) {
        ConceptInsightsId.validateGenarate(document, getAccountId(), corpusName);
        return executeRequest(version + document.getId(), null, Document.class);
    }
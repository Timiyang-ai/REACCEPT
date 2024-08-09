public void delete(Document document) throws CouchbaseLiteException {
        prepareDocument(document);
        document.delete();
    }
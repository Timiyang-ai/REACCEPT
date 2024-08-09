public void delete(Document document) {
        prepareDocument(document);
        document.delete();
    }
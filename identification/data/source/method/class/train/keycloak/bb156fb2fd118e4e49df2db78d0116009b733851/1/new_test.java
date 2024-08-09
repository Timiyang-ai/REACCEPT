    private PartialImportResults doImport() {
        Response response = testRealmResource().partialImport(piRep);
        return response.readEntity(PartialImportResults.class);
    }
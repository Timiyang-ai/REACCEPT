public Optional<DataSet> getDataSet(@Nonnull final String id, boolean encryptedCredentials) {
        try {
            return Optional.of(get(path("catalog", "dataset", id), 
                                   uri -> uri.queryParam("encrypt", encryptedCredentials),
                                   DataSet.class));
        } catch (final HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return Optional.empty();
            } else {
                throw e;
            }
        }
    }
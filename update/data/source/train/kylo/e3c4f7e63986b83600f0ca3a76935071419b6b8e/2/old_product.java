public Optional<DataSet> getDataSet(@Nonnull final String id) {
        try {
            return Optional.of(get(path("catalog", "dataset", id), DataSet.class));
        } catch (final HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return Optional.empty();
            } else {
                throw e;
            }
        }
    }
@Nonnull
    public static Optional<List<String>> getPaths(@Nonnull final DataSource dataSource) {
        if (dataSource.getTemplate() != null && dataSource.getTemplate().getPaths() != null) {
            return Optional.of(dataSource.getTemplate().getPaths());
        } else {
            return Optional.of(dataSource).map(DataSource::getConnector).map(Connector::getTemplate).map(DataSetTemplate::getPaths);
        }
    }
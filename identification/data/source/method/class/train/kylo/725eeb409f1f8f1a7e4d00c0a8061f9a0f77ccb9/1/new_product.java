@Nonnull
    public static Optional<List<String>> getPaths(@Nonnull final DataSource dataSource) {
        List<String> paths = new ArrayList<>();

        // Add "path" option
        if (dataSource.getTemplate() != null && dataSource.getTemplate().getOptions() != null && dataSource.getTemplate().getOptions().get("path") != null) {
            paths.add(dataSource.getTemplate().getOptions().get("path"));
        } else {
            Optional.of(dataSource).map(DataSource::getConnector).map(Connector::getTemplate).map(DataSetTemplate::getOptions).map(options -> options.get("path")).ifPresent(paths::add);
        }

        // Add paths list
        if (dataSource.getTemplate() != null && dataSource.getTemplate().getPaths() != null) {
            paths.addAll(dataSource.getTemplate().getPaths());
        } else if (dataSource.getConnector() != null && dataSource.getConnector().getTemplate() != null && dataSource.getConnector().getTemplate().getPaths() != null) {
            paths.addAll(dataSource.getConnector().getTemplate().getPaths());
        } else if (paths.isEmpty()) {
            paths = null;
        }

        return Optional.ofNullable(paths);
    }
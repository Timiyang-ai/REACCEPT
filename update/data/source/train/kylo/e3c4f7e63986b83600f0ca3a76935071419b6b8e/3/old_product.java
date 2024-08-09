@Nonnull
    public DataSet createDataSet(@Nonnull final DataSet source) {
        // Find data source
        final DataSource dataSource = Optional.of(source).map(DataSet::getDataSource).map(DataSource::getId).flatMap(dataSourceProvider::findDataSource)
            .orElseThrow(() -> new CatalogException("catalog.dataset.datasource.invalid"));

        // Validate data set
        final DataSet dataSet = new DataSet(source);
        dataSet.setDataSource(dataSource);
        validateDataSet(dataSet);

        // Create and store data set
        return metadataService.commit(() -> {
            // Resolve the real data set if possible, otherwise create
            com.thinkbiganalytics.metadata.api.catalog.DataSource.ID dataSourceId = dataSourceMetadataProvider.resolveId(dataSource.getId());
            com.thinkbiganalytics.metadata.api.catalog.DataSet ds = modelTransform.buildDataSet(source, metadataProvider.build(dataSourceId));
            return modelTransform.dataSetToRestModel().apply(ds);
        });
    }
@Test
    public void createDataSet() {
        Mockito.when(dataSetBuilder.build()).thenReturn(dataSet);
        Mockito.when(dataSetId.toString()).thenReturn("dataSet1");
        
        final com.thinkbiganalytics.kylo.catalog.rest.model.DataSource src = modelTransform.dataSourceToRestModel().apply(dataSource);
        final Response response = controller.createDataSet(new com.thinkbiganalytics.kylo.catalog.rest.model.DataSet(src, "MySQL Test"), true);
        
        assertThat(response.getEntity())
            .isNotNull()
            .extracting("id", "format").contains("dataSet1", "jdbc");
    }
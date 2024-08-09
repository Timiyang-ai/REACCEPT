@Test
    public void getDataSet() {
        Mockito.when(dataSetProvider.find(Mockito.any(DataSet.ID.class))).thenReturn(Optional.of(dataSet));
        Mockito.when(dataSetId.toString()).thenReturn("dataSet1");

        final Response response = controller.getDataSet("dataSet1", true);
        
        assertThat(response.getEntity())
            .isNotNull()
            .extracting("id", "format").contains("dataSet1", "jdbc");
    }
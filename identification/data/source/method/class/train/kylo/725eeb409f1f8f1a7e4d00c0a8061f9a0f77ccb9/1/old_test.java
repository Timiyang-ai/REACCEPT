    @Test
    public void getPaths() {
        // Create mock connector
        final DefaultDataSetTemplate connectorTemplate = new DefaultDataSetTemplate();
        connectorTemplate.setOptions(Collections.singletonMap("path", "connector1.txt"));
        connectorTemplate.setPaths(Collections.singletonList("connector2.txt"));

        final Connector connector = new Connector();
        connector.setTemplate(connectorTemplate);

        // Create mock data source
        final DefaultDataSetTemplate dataSourceTemplate = new DefaultDataSetTemplate();
        dataSourceTemplate.setOptions(Collections.singletonMap("path", "datasource1.txt"));
        dataSourceTemplate.setPaths(Collections.singletonList("datasource2.txt"));

        final DataSource dataSource = new DataSource();
        dataSource.setConnector(connector);
        dataSource.setTemplate(dataSourceTemplate);

        // Test retrieving data source paths
        Assert.assertEquals(Arrays.asList("datasource1.txt", "datasource2.txt"), DataSourceUtil.getPaths(dataSource).orElse(null));

        // Test retrieving connector paths
        dataSourceTemplate.setOptions(null);
        Assert.assertEquals(Arrays.asList("connector1.txt", "datasource2.txt"), DataSourceUtil.getPaths(dataSource).orElse(null));

        dataSourceTemplate.setPaths(null);
        Assert.assertEquals(Arrays.asList("connector1.txt", "connector2.txt"), DataSourceUtil.getPaths(dataSource).orElse(null));

        // Test retrieving empty paths
        connectorTemplate.setOptions(null);
        connectorTemplate.setPaths(null);
        Assert.assertEquals(Optional.empty(), DataSourceUtil.getPaths(dataSource));
    }
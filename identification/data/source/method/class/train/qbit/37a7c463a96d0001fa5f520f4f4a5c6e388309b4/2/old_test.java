    @Test
    public void getServices() throws Exception {
        Consul client = Consul.consul();
        CatalogEndpoint catalogClient = client.catalog();
        ConsulResponse<Map<String, List<String>>> services = catalogClient.getServices();

        assertTrue(services.getResponse().containsKey("consul"));
    }
    @Test
    public void getServiceInfos() {
        List<ServiceInfo> result = serviceManager.getServiceInfos(MapService.class);
        assertNotNull(result);
        assertEquals(1, result.size());

        ServiceInfo serviceInfo = result.get(0);
        assertEquals(MapService.SERVICE_NAME, serviceInfo.getName());
        assertInstanceOf(MapService.class, serviceInfo.getService());
    }
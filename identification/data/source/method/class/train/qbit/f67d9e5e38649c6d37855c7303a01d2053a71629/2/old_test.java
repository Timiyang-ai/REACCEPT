    @Test
    public void deregister() throws Exception {
        Consul client = Consul.consul();
        String serviceName = UUID.randomUUID().toString();
        String serviceId = UUID.randomUUID().toString();

        client.agent().registerService("localhost", 8080, 10000L, serviceName, serviceId);
        client.agent().deregister(serviceId);
        Thread.sleep(1000L);
        boolean found = false;

        for (ServiceHealth health : client.health().getAllNodes(serviceName).getResponse()) {
            if (health.getService().getId().equals(serviceId)) {
                found = true;
            }
        }

        assertFalse(found);
    }
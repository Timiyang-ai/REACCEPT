    @Test
    public void register() throws UnknownHostException {
        Consul client = Consul.consul();

        String serviceName = UUID.randomUUID().toString();
        String serviceId = UUID.randomUUID().toString();

        client.agent().registerService("localhost", 80, 20000L, serviceName, serviceId);

        boolean found = false;

        for (ServiceHealth health : client.health().getAllNodes(serviceName).getResponse()) {
            if (health.getService().getId().equals(serviceId)) {
                found = true;
            }
        }

        assertTrue(found);
    }
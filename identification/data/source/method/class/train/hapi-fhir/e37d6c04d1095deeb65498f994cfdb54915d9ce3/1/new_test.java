@Test
    public void clean() {
        RemoveDstu2TestIT.deleteResources(Subscription.class, null, client);
        RemoveDstu2TestIT.deleteResources(Observation.class, null, client);
    }
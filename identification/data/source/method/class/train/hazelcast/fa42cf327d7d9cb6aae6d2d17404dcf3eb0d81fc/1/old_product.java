private void setup() {
        assert (bounceTestConfig.getClusterSize() > 1) : "Cluster size must be at least 2.";

        if (bounceTestConfig.getDriverType() == CLIENT) {
            factory = newTestHazelcastFactory();
        } else {
            factory = new TestHazelcastInstanceFactory();
        }
        Config memberConfig = bounceTestConfig.getMemberConfig();
        members = new HazelcastInstance[bounceTestConfig.getClusterSize()];
        for (int i = 0; i < bounceTestConfig.getClusterSize(); i++) {
            members[i] = factory.newHazelcastInstance(memberConfig);
        }

        // setup drivers
        testDrivers = bounceTestConfig.getDriverFactory().createTestDrivers(this);

        testRunning.set(true);
    }
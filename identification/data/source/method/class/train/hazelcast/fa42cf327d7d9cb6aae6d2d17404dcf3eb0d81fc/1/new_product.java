private void setup() {
        assert (bounceTestConfig.getClusterSize() > 1) : "Cluster size must be at least 2.";

        if (bounceTestConfig.getDriverType() == CLIENT) {
            factory = newTestHazelcastFactory();
        } else {
            factory = new TestHazelcastInstanceFactory();
        }
        Config memberConfig = bounceTestConfig.getMemberConfig();
        for (int i = 0; i < bounceTestConfig.getClusterSize(); i++) {
            members.set(i, factory.newHazelcastInstance(memberConfig));
        }

        // setup drivers
        HazelcastInstance[] drivers = bounceTestConfig.getDriverFactory().createTestDrivers(this);
        assert drivers.length == bounceTestConfig.getDriverCount()
                : "Driver factory should return " + bounceTestConfig.getDriverCount() + " test drivers.";
        for (int i = 0; i < drivers.length; i++) {
            testDrivers.set(i, drivers[i]);
        }
        testRunning.set(true);
    }
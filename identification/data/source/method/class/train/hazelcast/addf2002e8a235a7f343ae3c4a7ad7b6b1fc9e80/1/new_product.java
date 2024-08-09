public static HazelcastInstance getOrCreateHazelcastInstance(Config config) {
        return HazelcastInstanceManager.getOrCreateHazelcastInstance(config);
    }
    @Test(expected = IllegalArgumentException.class)
    public void getOrCreateHazelcastInstance_nullConfig() {
        Hazelcast.getOrCreateHazelcastInstance(null);
    }
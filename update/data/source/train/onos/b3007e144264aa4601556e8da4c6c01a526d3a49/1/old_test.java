@Test
    public void testSetHostLearning() throws Exception {
        config.setHostLearning(true);
        assertTrue(config.hostLearning());
    }
@Test
    public void testSetGlobalOrderingEnabled() {
        TopicConfig topicConfig = new TopicConfig().setGlobalOrderingEnabled(true);
        assertTrue(topicConfig.isGlobalOrderingEnabled());
        try {
        	topicConfig.setMultiThreadingEnabled(true);
        	assertTrue("multi-threading must be disabled when global-ordering is enabled", false);
        } catch (IllegalArgumentException e) {
        	// anticipated..
        }
        assertFalse(topicConfig.isMultiThreadingEnabled());
    }
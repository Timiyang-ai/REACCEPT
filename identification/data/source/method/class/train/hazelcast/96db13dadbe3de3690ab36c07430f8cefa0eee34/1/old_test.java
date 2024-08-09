@Test
    public void testSetName() {
        QueueConfig queueConfig = new QueueConfig().setName("a test name");
        assertEquals("a test name", queueConfig.getName());
    }
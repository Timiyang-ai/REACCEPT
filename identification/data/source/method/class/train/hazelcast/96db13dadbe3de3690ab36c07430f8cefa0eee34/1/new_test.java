@Test
    public void testSetName() {
        QueueConfig queueConfig = new QueueConfig().setName("a test name");
        assertEquals("a test name", queueConfig.getName());
        assertEquals("default", queueConfig.getBackingMapName());
        queueConfig.setBackingMapName("backingMap");
        assertEquals("backingMap", queueConfig.getBackingMapName());
    }
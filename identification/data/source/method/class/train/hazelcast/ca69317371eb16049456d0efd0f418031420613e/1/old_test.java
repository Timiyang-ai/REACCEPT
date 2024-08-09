@Test
    public void testSetName() {
        String name = "a test name";
        QueueConfig queueConfig = new QueueConfig().setName(name);
        assertEquals(name, queueConfig.getName());
        assertEquals("q:" + name, queueConfig.getBackingMapName());
        queueConfig.setBackingMapName("backingMap");
        assertEquals("backingMap", queueConfig.getBackingMapName());
    }
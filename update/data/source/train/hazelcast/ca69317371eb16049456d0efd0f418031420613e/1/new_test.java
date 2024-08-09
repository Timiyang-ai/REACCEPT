@Test
    public void testSetName() {
        String name = "a test name";
        QueueConfig queueConfig = new QueueConfig().setName(name);
        assertEquals(name, queueConfig.getName());
        assertEquals("q:" + name, queueConfig.getBackingMapRef());
        queueConfig.setBackingMapRef("backingMap");
        assertEquals("backingMap", queueConfig.getBackingMapRef());
    }
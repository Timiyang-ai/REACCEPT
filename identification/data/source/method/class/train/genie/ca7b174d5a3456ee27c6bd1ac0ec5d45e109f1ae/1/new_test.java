@Test
    public void testOnUpdateBaseEntity() throws InterruptedException {
        final BaseEntity a = new BaseEntity();
        Assert.assertNull(a.getId());
        Assert.assertNotNull(a.getCreated());
        Assert.assertNotNull(a.getUpdated());
        a.onCreateBaseEntity();
        final Date originalCreate = a.getCreated();
        final Date originalUpdate = a.getUpdated();
        Thread.sleep(1);
        a.onUpdateBaseEntity();
        Assert.assertEquals(originalCreate, a.getCreated());
        Assert.assertNotEquals(originalUpdate, a.getUpdated());
    }
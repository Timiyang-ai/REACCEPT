@Test
    public void testOnCreateAuditable() throws InterruptedException, GeniePreconditionException {
        final BaseEntity a = new BaseEntity();
        Assert.assertNull(a.getId());
        Assert.assertNotNull(a.getCreated());
        Assert.assertNotNull(a.getUpdated());
        final Date originalCreated = a.getCreated();
        final Date originalUpdated = a.getUpdated();
        Thread.sleep(1);
        a.onCreateAuditable();
        Assert.assertNotNull(a.getId());
        Assert.assertNotNull(a.getCreated());
        Assert.assertNotNull(a.getUpdated());
        Assert.assertNotEquals(originalCreated, a.getCreated());
        Assert.assertNotEquals(originalUpdated, a.getUpdated());
        Assert.assertEquals(a.getCreated(), a.getUpdated());

        //Test to make sure if an ID already was set we don't change it
        final BaseEntity baseEntity = new BaseEntity();
        final String id = UUID.randomUUID().toString();
        baseEntity.setId(id);
        baseEntity.onCreateAuditable();
        Assert.assertEquals(id, baseEntity.getId());
    }
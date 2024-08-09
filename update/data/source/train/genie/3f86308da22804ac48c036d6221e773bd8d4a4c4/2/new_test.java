@Test
    public void testSetCreated() {
        final Auditable a = new Auditable();
        Assert.assertNull(a.getCreated());
        a.setCreated(new Date());
        Assert.assertNull(a.getCreated());
        a.onCreateAuditable();
        Assert.assertNotNull(a.getCreated());
    }
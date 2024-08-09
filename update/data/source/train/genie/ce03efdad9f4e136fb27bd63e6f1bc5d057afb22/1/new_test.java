@Test
    public void testSetCreated() {
        final Auditable a = new Auditable();
        Assert.assertNotNull(a.getCreated());
        final Date date = new Date(0);
        a.setCreated(date);
        Assert.assertNotNull(a.getCreated());
        Assert.assertEquals(date, a.getCreated());
        a.onCreateAuditable();
        Assert.assertNotNull(a.getCreated());
        Assert.assertNotEquals(date, a.getCreated());
    }
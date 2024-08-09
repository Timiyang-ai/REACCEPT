@Test
    public void testSetUpdated() {
        final Auditable a = new Auditable();
        Assert.assertNull(a.getUpdated());
        final Date newer = new Date();
        a.setUpdated(newer);
        Assert.assertNull(a.getUpdated());
    }
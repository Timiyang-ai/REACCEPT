@Test
    public void testSetUpdated() {
        final Auditable a = new Auditable();
        final Date ou = a.getUpdated();
        final Date newer = new Date(ou.getTime() + 1);
        a.setUpdated(newer);
        Assert.assertEquals(newer, a.getUpdated());
    }
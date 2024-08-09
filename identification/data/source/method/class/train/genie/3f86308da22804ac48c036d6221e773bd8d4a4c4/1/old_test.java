@Test
    public void testSetCreated() {
        final Auditable a = new Auditable();
        final Date oc = a.getCreated();
        final Date newer = new Date(oc.getTime() + 1);
        final Date older = new Date(oc.getTime() - 1);
        a.setCreated(newer);
        Assert.assertEquals(oc, a.getCreated());
        a.setCreated(older);
        Assert.assertEquals(older, a.getCreated());
    }
@Test
    public void testSetId() throws GeniePreconditionException {
        final Auditable a = new Auditable();
        Assert.assertNull(a.getId());
        final String id = UUID.randomUUID().toString();
        a.setId(id);
        Assert.assertEquals(id, a.getId());
    }
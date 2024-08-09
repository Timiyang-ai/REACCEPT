@Test
    public void testSetId() throws GenieException {
        final Auditable a = new Auditable();
        Assert.assertNull(a.getId());
        final String id = UUID.randomUUID().toString();
        a.setId(id);
        Assert.assertEquals(id, a.getId());
    }
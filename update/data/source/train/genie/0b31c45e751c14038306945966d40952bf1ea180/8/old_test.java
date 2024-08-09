@Test
    public void testSetTags() throws GenieException {
        Assert.assertNotNull(this.c.getTags());
        final Set<String> tags = new HashSet<>();
        tags.add("prod");
        tags.add("sla");
        this.c.setTags(tags);
        Assert.assertEquals(tags, this.c.getTags());
    }
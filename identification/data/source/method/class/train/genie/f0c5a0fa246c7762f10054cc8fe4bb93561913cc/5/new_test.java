@Test
    public void testSetTags() {
        Assert.assertNotNull(this.c.getTags());
        Assert.assertTrue(this.c.getTags().isEmpty());
        final Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        this.c.setTags(tags);
        Assert.assertEquals(tags, this.c.getTags());
    }
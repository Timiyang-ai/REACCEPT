@Test
    public void testSetTags() {
        Assert.assertNull(this.c.getTags());
        final Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        this.c.setTags(tags);
        Assert.assertEquals(tags, this.c.getTags());
    }
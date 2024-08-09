    @Test
    public void getTags() {
        Definitions instance = new Definitions();
        assertEquals(instance.getTags().size(), 0);
        instance.addTag(1, "one", "", "", 0, 0);
        assertEquals(instance.getTags().size(), 1);
        instance.addTag(1, "two", "", "", 0, 0);
        assertEquals(instance.getTags().size(), 2);
        instance.addTag(3, "two", "", "", 0, 0);
        assertEquals(instance.getTags().size(), 3);
    }
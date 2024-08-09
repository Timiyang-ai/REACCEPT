    @Test
    public void getRevision() {
        Annotation instance = new Annotation("testfile.tst");
        assertEquals(instance.getRevision(1), "");
        instance.addLine("1.0", "Author", true);
        assertEquals(instance.getRevision(1), "1.0");
        instance.addLine("1.1.0", "Author 2", false);
        assertEquals(instance.getRevision(2), "1.1.0");
    }
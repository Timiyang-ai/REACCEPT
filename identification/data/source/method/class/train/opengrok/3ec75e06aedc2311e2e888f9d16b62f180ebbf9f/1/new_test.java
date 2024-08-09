    @Test
    public void addLine() {
        Annotation instance = new Annotation("testfile.tst");
        instance.addLine("1.0", "Author", true);
        assertEquals(instance.size(), 1);
        instance.addLine(null, null, true);
    }
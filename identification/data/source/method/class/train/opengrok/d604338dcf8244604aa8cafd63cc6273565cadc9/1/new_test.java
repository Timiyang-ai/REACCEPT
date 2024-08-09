    @Test
    public void getAuthor() {
        Annotation instance = new Annotation("testfile.tst");
        assertEquals(instance.getAuthor(1), "");
        instance.addLine("1.0", "Author", true);
        assertEquals(instance.getAuthor(1), "Author");
        instance.addLine("1.1.0", "Author 2", false);
        assertEquals(instance.getAuthor(2), "Author 2");
    }
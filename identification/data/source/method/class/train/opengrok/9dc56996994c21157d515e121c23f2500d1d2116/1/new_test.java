    @Test
    public void strip() {
        TreeSet<String> files = new TreeSet<String>();
        files.add("file1.file");
        files.add("file2.file");
        instance.setFiles(files);
        instance.setTags("test tag");
        instance.strip();
        assertEquals(0, instance.getFiles().size());
        assertEquals(null, instance.getTags());
    }
    public void test_createNewFile() throws Exception {
        File f = File.createTempFile("FileTest", "tmp");
        assertFalse(f.createNewFile()); // EEXIST -> false
        try {
            new File(f, "poop").createNewFile(); // ENOTDIR -> throw
            fail();
        } catch (IOException expected) {
        }
        try {
            new File("").createNewFile(); // ENOENT -> throw
            fail();
        } catch (IOException expected) {
        }
    }
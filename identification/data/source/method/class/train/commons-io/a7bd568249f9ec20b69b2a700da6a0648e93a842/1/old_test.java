@Test
    public void testGetPrefixLength() {
        assertEquals(-1, FilenameUtils.getPrefixLength(null));
        assertEquals(-1, FilenameUtils.getPrefixLength(":"));
        assertEquals(-1, FilenameUtils.getPrefixLength("1:\\a\\b\\c.txt"));
        assertEquals(-1, FilenameUtils.getPrefixLength("1:"));
        assertEquals(-1, FilenameUtils.getPrefixLength("1:a"));
        assertEquals(-1, FilenameUtils.getPrefixLength("\\\\\\a\\b\\c.txt"));
        assertEquals(-1, FilenameUtils.getPrefixLength("\\\\a"));

        assertEquals(0, FilenameUtils.getPrefixLength(""));
        assertEquals(1, FilenameUtils.getPrefixLength("\\"));
        assertEquals(2, FilenameUtils.getPrefixLength("C:"));
        assertEquals(3, FilenameUtils.getPrefixLength("C:\\"));
        assertEquals(9, FilenameUtils.getPrefixLength("//server/"));
        assertEquals(2, FilenameUtils.getPrefixLength("~"));
        assertEquals(2, FilenameUtils.getPrefixLength("~/"));
        assertEquals(6, FilenameUtils.getPrefixLength("~user"));
        assertEquals(6, FilenameUtils.getPrefixLength("~user/"));

        assertEquals(0, FilenameUtils.getPrefixLength("a\\b\\c.txt"));
        assertEquals(1, FilenameUtils.getPrefixLength("\\a\\b\\c.txt"));
        assertEquals(2, FilenameUtils.getPrefixLength("C:a\\b\\c.txt"));
        assertEquals(3, FilenameUtils.getPrefixLength("C:\\a\\b\\c.txt"));
        assertEquals(9, FilenameUtils.getPrefixLength("\\\\server\\a\\b\\c.txt"));

        assertEquals(0, FilenameUtils.getPrefixLength("a/b/c.txt"));
        assertEquals(1, FilenameUtils.getPrefixLength("/a/b/c.txt"));
        assertEquals(3, FilenameUtils.getPrefixLength("C:/a/b/c.txt"));
        assertEquals(9, FilenameUtils.getPrefixLength("//server/a/b/c.txt"));
        assertEquals(2, FilenameUtils.getPrefixLength("~/a/b/c.txt"));
        assertEquals(6, FilenameUtils.getPrefixLength("~user/a/b/c.txt"));

        assertEquals(0, FilenameUtils.getPrefixLength("a\\b\\c.txt"));
        assertEquals(1, FilenameUtils.getPrefixLength("\\a\\b\\c.txt"));
        assertEquals(2, FilenameUtils.getPrefixLength("~\\a\\b\\c.txt"));
        assertEquals(6, FilenameUtils.getPrefixLength("~user\\a\\b\\c.txt"));

        assertEquals(9, FilenameUtils.getPrefixLength("//server/a/b/c.txt"));
        assertEquals(-1, FilenameUtils.getPrefixLength("\\\\\\a\\b\\c.txt"));
        assertEquals(-1, FilenameUtils.getPrefixLength("///a/b/c.txt"));
    }
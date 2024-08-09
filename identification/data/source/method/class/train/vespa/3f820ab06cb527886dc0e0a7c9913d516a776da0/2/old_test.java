    @Test
    public void deleteRecursively() throws Exception {
        // Create the following file tree:
        //
        // /dir1
        //  |--- dir2
        //      |--- file1
        // /link1 -> /dir1/dir2
        //
        var dir1 = fs.getPath("/dir1");
        var dir2 = dir1.resolve("dir2");
        var file1 = dir2.resolve("file1");
        Files.createDirectories(dir2);
        Files.writeString(file1, "file1");
        var link1 = Files.createSymbolicLink(fs.getPath("/link1"), dir2);

        new UnixPath(link1).deleteRecursively();
        assertTrue("Deleting " + link1 + " recursively does not remove " + dir2, Files.exists(dir2));
        assertTrue("Deleting " + link1 + " recursively does not remove " + file1, Files.exists(file1));

        new UnixPath(dir1).deleteRecursively();
        assertFalse(dir1 + " deleted recursively", Files.exists(file1));
        assertFalse(dir1 + " deleted recursively", Files.exists(dir2));
        assertFalse(dir1 + " deleted recursively", Files.exists(dir1));
    }
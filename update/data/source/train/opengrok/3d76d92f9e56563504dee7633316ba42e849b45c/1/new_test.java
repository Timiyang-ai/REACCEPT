@Test
    public void testGetPathRelativeToSourceRoot() throws IOException,
            ForbiddenSymlinkException {
        RuntimeEnvironment env = RuntimeEnvironment.getInstance();

        // Create and set source root.
        File sourceRoot = FileUtilities.createTemporaryDirectory("src");
        assertTrue(sourceRoot.exists());
        assertTrue(sourceRoot.isDirectory());
        env.setSourceRoot(sourceRoot.getPath());

        // Create directory underneath source root and check.
        String filename = "foo";
        File file = new File(env.getSourceRootFile(), filename);
        file.createNewFile();
        assertTrue(file.exists());
        assertEquals(File.separator + filename,
                env.getPathRelativeToSourceRoot(file));

        // Create symlink underneath source root.
        String symlinkName = "symlink";
        File realDir = FileUtilities.createTemporaryDirectory("realdir");
        File symlink = new File(sourceRoot, symlinkName);
        Files.createSymbolicLink(
                Paths.get(symlink.getPath()),
                Paths.get(realDir.getPath()));
        assertTrue(symlink.exists());
        env.setAllowedSymlinks(new HashSet<>());
        ForbiddenSymlinkException expex = null;
        try {
            env.getPathRelativeToSourceRoot(symlink);
        } catch (ForbiddenSymlinkException e) {
            expex = e;
        }
        assertTrue("getPathRelativeToSourceRoot() should have thrown " +
            "IOexception for symlink that is not allowed", expex != null);

        // Allow the symlink and retest.
        env.setAllowedSymlinks(new HashSet<>(Arrays.asList(symlink.getPath())));
        assertEquals(File.separator + symlinkName,
                env.getPathRelativeToSourceRoot(symlink));

        // cleanup
        IOUtils.removeRecursive(sourceRoot.toPath());
        IOUtils.removeRecursive(realDir.toPath());
    }
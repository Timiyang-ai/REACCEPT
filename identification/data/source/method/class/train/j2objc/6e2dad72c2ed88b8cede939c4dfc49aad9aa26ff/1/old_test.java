    public void test_getCanonicalPath() throws Exception {
        // This assumes you can create symbolic links in the temporary directory. This isn't
        // true on Android if you're using /sdcard. It will work in /data/local though.
        File base = createTemporaryDirectory();
        File target = new File(base, "target");
        target.createNewFile(); // The RI won't follow a dangling symlink, which seems like a bug!
        File linkName = new File(base, "link");
        ln_s(target, linkName);
        assertEquals(target.getCanonicalPath(), linkName.getCanonicalPath());

        // .../subdir/shorter -> .../target (using a link to ../target).
        File subdir = new File(base, "subdir");
        assertTrue(subdir.mkdir());
        linkName = new File(subdir, "shorter");
        ln_s("../target", linkName.toString());
        assertEquals(target.getCanonicalPath(), linkName.getCanonicalPath());

        // .../l -> .../subdir/longer (using a relative link to subdir/longer).
        linkName = new File(base, "l");
        ln_s("subdir/longer", linkName.toString());
        File longer = new File(base, "subdir/longer");
        longer.createNewFile(); // The RI won't follow a dangling symlink, which seems like a bug!
        assertEquals(longer.getCanonicalPath(), linkName.getCanonicalPath());

        // .../double -> .../target (via a link into subdir and a link back out).
        linkName = new File(base, "double");
        ln_s("subdir/shorter", linkName.toString());
        assertEquals(target.getCanonicalPath(), linkName.getCanonicalPath());
    }
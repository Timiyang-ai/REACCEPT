@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "entries",
        args = {}
    )
    public void test_entries() throws Exception {
        // Test for method java.util.Enumeration java.util.zip.ZipFile.entries()
        Enumeration<? extends ZipEntry> enumer = zfile.entries();
        int c = 0;
        while (enumer.hasMoreElements()) {
            ++c;
            enumer.nextElement();
        }
        assertTrue("Incorrect number of entries returned: " + c, c == 6);

        Enumeration<? extends ZipEntry> enumeration = zfile.entries();
        zfile.close();
        zfile = null;
        boolean pass = false;
        try {
            enumeration.hasMoreElements();
        } catch (IllegalStateException e) {
            pass = true;
        }
        assertTrue("did not detect closed jar file", pass);
    }
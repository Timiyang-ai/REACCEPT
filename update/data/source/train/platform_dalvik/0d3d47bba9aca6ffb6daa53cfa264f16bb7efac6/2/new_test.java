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
        try {
            enumeration.nextElement();
            fail("did not detect closed file");
        } catch (IllegalStateException expected) {
        }

        try {
            enumeration.hasMoreElements();
            fail("did not detect closed file");
        } catch (IllegalStateException expected) {
        }

        try {
            zfile.entries();
            fail("did not detect closed file");
        } catch (IllegalStateException expected) {
        }
    }
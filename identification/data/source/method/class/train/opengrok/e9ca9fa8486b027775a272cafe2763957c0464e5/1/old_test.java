@Test
    public void getRevision() throws Exception {
        if (!haveSccs) {
            System.out.println("sccs not available. Skipping test");
            return;
        }
        ZipInputStream zstream = new ZipInputStream(SCCSgetTest.class.getResourceAsStream("sccs-revisions.zip"));
        ZipEntry entry;

        while ((entry = zstream.getNextEntry()) != null) {
            String expected = readInput(zstream);
            InputStream sccs = SCCSget.getRevision(sccsfile, entry.getName());
            String got = readInput(sccs);
            sccs.close();
            zstream.closeEntry();
            assertEquals(expected, got);
        }
        zstream.close();
    }
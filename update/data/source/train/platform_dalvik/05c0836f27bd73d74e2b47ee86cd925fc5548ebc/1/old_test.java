@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "ZipException can not be checked.",
        method = "closeEntry",
        args = {}
    )
    public void test_closeEntry() throws IOException {
        ZipEntry ze = new ZipEntry("testEntry");
        ze.setTime(System.currentTimeMillis());
        zos.putNextEntry(ze);
        zos.write("Hello World".getBytes());
        zos.closeEntry();
        assertTrue("closeEntry failed to update required fields",
                ze.getSize() == 11 && ze.getCompressedSize() == 13);
        ze = new ZipEntry("testEntry1");
        zos.close();
        try {
            zos.closeEntry();
            fail("IOException expected");
        } catch (IOException ee) {
            // expected
        }
    }
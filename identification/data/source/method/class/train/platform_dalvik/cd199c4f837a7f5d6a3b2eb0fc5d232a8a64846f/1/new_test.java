public void test_getNextEntry() throws Exception {
        assertNotNull("getNextEntry failed", zis.getNextEntry());

        File resources = Support_Resources.createTempFolder();
        Support_Resources.copyFile(resources, null, "Broken_manifest.jar");
        FileInputStream fis = new FileInputStream(new File(resources,
                "Broken_manifest.jar"));

        ZipInputStream zis1 = new ZipInputStream(fis);

        try {
            for (int i = 0; i < 6; i++) {
                zis1.getNextEntry();
            }
            fail("ZipException expected");
        } catch (ZipException ee) {
            // expected
        }

        try {
            zis1.close();  // Android throws exception here, already!
            zis1.getNextEntry();  // But RI here, only!
            fail("IOException expected");
        } catch (IOException ee) {
            // expected
        }
    }
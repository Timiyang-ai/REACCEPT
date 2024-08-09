@Test
    public void testFindNRecentSnapshots() throws Exception {
        int nRecentSnap = 4; // n recent snap shots
        int nRecentCount = 30;
        int offset = 0;
        tmpDir = ClientBase.createTmpDir();
        File version2 = new File(tmpDir.toString(), "version-2");
        Assert.assertTrue("Failed to create version_2 dir:" + version2.toString(),
                version2.mkdir());
        List<File> expectedNRecentSnapFiles = new ArrayList<File>();
        int counter = offset + (2 * nRecentCount);
        for (int i = 0; i < nRecentCount; i++) {
            // simulate log file
            File logFile = new File(version2 + "/log." + Long.toHexString(--counter));
            Assert.assertTrue("Failed to create log File:" + logFile.toString(),
                    logFile.createNewFile());
            // simulate snapshot file
            File snapFile = new File(version2 + "/snapshot."
                    + Long.toHexString(--counter));
            Assert.assertTrue("Failed to create snap File:" + snapFile.toString(),
                    snapFile.createNewFile());
            // add the n recent snap files for assertion
            if(i < nRecentSnap){
                expectedNRecentSnapFiles.add(snapFile);
            }
        }

        FileTxnSnapLog txnLog = new FileTxnSnapLog(tmpDir, tmpDir);
        List<File> nRecentSnapFiles = txnLog.findNRecentSnapshots(nRecentSnap);
        txnLog.close();
        Assert.assertEquals("exactly 4 snapshots ", 4,
                nRecentSnapFiles.size());
        expectedNRecentSnapFiles.removeAll(nRecentSnapFiles);
        Assert.assertEquals("Didn't get the recent snap files", 0,
                expectedNRecentSnapFiles.size());
    }
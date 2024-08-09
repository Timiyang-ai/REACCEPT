@Test
    public void testFindNRecentSnapshots() throws Exception {
        int nRecentSnap = 4; // n recent snap shots
        int nRecentCount = 30;
        int offset = 0;

        tmpDir = ClientBase.createTmpDir();
        File version2 = new File(tmpDir.toString(), "version-2");
        Assert.assertTrue("Failed to create version_2 dir:" + version2.toString(),
                version2.mkdir());

        // Test that with no snaps, findNRecentSnapshots returns empty list
        FileTxnSnapLog txnLog = new FileTxnSnapLog(tmpDir, tmpDir);
        List<File> foundSnaps = txnLog.findNRecentSnapshots(1);
        assertEquals(0, foundSnaps.size());

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

        // Test that when we ask for recent snaps we get the number we asked for and
        // the files we expected
        List<File> nRecentSnapFiles = txnLog.findNRecentSnapshots(nRecentSnap);
        Assert.assertEquals("exactly 4 snapshots ", 4,
                nRecentSnapFiles.size());
        expectedNRecentSnapFiles.removeAll(nRecentSnapFiles);
        Assert.assertEquals("Didn't get the recent snap files", 0,
                expectedNRecentSnapFiles.size());

        // Test that when asking for more snaps than we created, we still only get snaps
        // not logs or anything else (per ZOOKEEPER-2420)
        nRecentSnapFiles = txnLog.findNRecentSnapshots(nRecentCount + 5);
        assertEquals(nRecentCount, nRecentSnapFiles.size());
        for (File f: nRecentSnapFiles) {
            Assert.assertTrue("findNRecentSnapshots() returned a non-snapshot: " + f.getPath(),
                   (Util.getZxidFromName(f.getName(), "snapshot") != -1));
        }

        txnLog.close();
    }
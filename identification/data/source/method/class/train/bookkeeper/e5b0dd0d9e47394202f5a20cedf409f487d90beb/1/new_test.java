@Test
    public void testShutdown() throws Exception {
        BookieServer auditor = verifyAuditor();
        shutdownBookie(auditor);

        // waiting for new auditor
        BookieServer newAuditor = waitForNewAuditor(auditor);
        Assert.assertNotSame(
                "Auditor re-election is not happened for auditor failure!",
                auditor, newAuditor);
        int indexOfDownBookie = bs.indexOf(auditor);
        bs.remove(indexOfDownBookie);
        bsConfs.remove(indexOfDownBookie);
        tmpDirs.remove(indexOfDownBookie);
        List<String> children = zkc.getChildren(electionPath, false);
        for (String child : children) {
            byte[] data = zkc.getData(electionPath + '/' + child, false, null);
            String bookieIP = new String(data);
            String addr = StringUtils.addrToString(auditor.getLocalAddress());
            Assert.assertFalse("AuditorElection cleanup fails", bookieIP
                    .contains(addr));
        }
    }
@Test
    public void testWriteToZooKeeper() throws Exception {
        String[] ledgerDirs = new String[] { newDirectory(), newDirectory(), newDirectory() };
        String journalDir = newDirectory();
        ServerConfiguration conf = TestBKConfiguration.newServerConfiguration()
                .setZkServers(zkUtil.getZooKeeperConnectString()).setJournalDirName(journalDir)
                .setLedgerDirNames(ledgerDirs).setBookiePort(bookiePort);
        Bookie b = new Bookie(conf); // should work fine
        b.start();
        b.shutdown();
        Versioned<Cookie> zkCookie = Cookie.readFromZooKeeper(zkc, conf);
        Version version1 = zkCookie.getVersion();
        Assert.assertTrue("Invalid type expected ZkVersion type",
            version1 instanceof LongVersion);
        LongVersion zkVersion1 = (LongVersion) version1;
        Cookie cookie = zkCookie.getValue();
        cookie.writeToZooKeeper(zkc, conf, version1);

        zkCookie = Cookie.readFromZooKeeper(zkc, conf);
        Version version2 = zkCookie.getVersion();
        Assert.assertTrue("Invalid type expected ZkVersion type",
            version2 instanceof LongVersion);
        LongVersion zkVersion2 = (LongVersion) version2;
        Assert.assertEquals("Version mismatches!",
            zkVersion1.getLongVersion() + 1, zkVersion2.getLongVersion());
    }
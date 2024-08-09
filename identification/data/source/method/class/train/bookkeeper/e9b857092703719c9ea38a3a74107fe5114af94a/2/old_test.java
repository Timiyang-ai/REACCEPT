@Test
    public void testDeleteFromZooKeeper() throws Exception {
        String[] ledgerDirs = new String[] { newDirectory(), newDirectory(), newDirectory() };
        String journalDir = newDirectory();
        ServerConfiguration conf = TestBKConfiguration.newServerConfiguration()
                .setZkServers(zkUtil.getZooKeeperConnectString()).setJournalDirName(journalDir)
                .setLedgerDirNames(ledgerDirs).setBookiePort(bookiePort);
        Bookie b = new Bookie(conf); // should work fine
        b.start();
        b.shutdown();
        Versioned<Cookie> zkCookie = Cookie.readFromZooKeeper(zkc, conf);
        Cookie cookie = zkCookie.getValue();
        cookie.deleteFromZooKeeper(zkc, conf, zkCookie.getVersion());
    }
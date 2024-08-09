@Test
    public void testClose() {
        FutureReadResultEntryCollection c = new FutureReadResultEntryCollection();
        List<FutureReadResultEntry> entries = generateEntries();
        entries.forEach(c::add);
        c.close();

        for (FutureReadResultEntry e : entries) {
            Assert.assertTrue("StorageReadResultEntry is not canceled.", e.getContent().isCancelled());
        }
    }
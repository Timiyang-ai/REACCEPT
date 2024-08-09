@Test
    public void testClose() {
        FutureReadResultEntryCollection c = new FutureReadResultEntryCollection();
        List<FutureReadResultEntry> entries = generateEntries();
        entries.forEach(c::add);
        val result = c.close();

        for (FutureReadResultEntry e : entries) {
            Assert.assertFalse("StorageReadResultEntry is completed.", e.getContent().isCancelled());
        }

        AssertExtensions.assertListEquals("Unexpected result from close().", entries, result, Object::equals);
    }
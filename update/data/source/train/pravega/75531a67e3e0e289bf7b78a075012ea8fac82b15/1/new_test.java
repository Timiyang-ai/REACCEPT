@Test
    public void testUpdate() {
        final int count = 10000;
        val ds = new DataSource();
        val index = defaultBuilder(ds).build();
        index.initialize(TIMEOUT).join();
        val entries = generate(count);
        sort(entries);
        index.update(entries, TIMEOUT).join();

        // Delete every 1/3 of the keys
        val toUpdate = new ArrayList<PageEntry>();
        val expectedEntries = new ArrayList<PageEntry>(entries);
        val rnd = new Random(0);
        for (int i = entries.size() - 1; i >= 0; i--) {
            PageEntry e = expectedEntries.get(i);
            boolean delete = i % 3 == 0;
            boolean update = i % 2 == 0;
            if (delete && !update) {
                // Delete about 1/3 of the entries.
                toUpdate.add(PageEntry.noValue(expectedEntries.get(i).getKey()));
                    expectedEntries.remove(i);
            }

            if (update) {
                // Update (reinsert or update) 1/2 of the entries.
                val newValue = new byte[VALUE_LENGTH];
                rnd.nextBytes(newValue);
                e = new PageEntry(e.getKey(), new ByteArraySegment(newValue));
                toUpdate.add(e);
                expectedEntries.set(i, e);
            }
        }

        // Perform the removals and updates.
        index.update(toUpdate, TIMEOUT).join();

        // Verify final result.
        check("Unexpected index contents.", index, expectedEntries, 0);
    }
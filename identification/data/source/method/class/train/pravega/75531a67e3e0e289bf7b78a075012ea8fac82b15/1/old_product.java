void update(@NonNull Collection<PageEntry> entries) {
        if (entries.isEmpty()) {
            // Nothing to do.
            return;
        }

        // Apply the in-place updates and collect the new entries to be added.
        val newEntries = applyUpdates(entries);
        if (newEntries.isEmpty()) {
            // Nothing else to change. We've already updated the keys in-place.
            return;
        }

        val newPage = insertNewEntries(newEntries);

        // Make sure we swap all the segments with those from the new page. We need to release all pointers to our
        // existing buffers.
        this.header = newPage.header;
        this.data = newPage.data;
        this.contents = newPage.contents;
        this.footer = newPage.footer;
        this.count = newPage.count;
    }
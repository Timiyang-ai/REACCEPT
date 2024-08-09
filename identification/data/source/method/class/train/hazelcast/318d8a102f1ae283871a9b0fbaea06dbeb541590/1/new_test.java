    private void addEnd(int numberOfEntriesToAdd, WriteBehindQueue<DelayedEntry> queue) {
        List<DelayedEntry> delayedEntries = createDelayedEntryList(numberOfEntriesToAdd);
        for (DelayedEntry entry : delayedEntries) {
            queue.addLast(entry, false);
        }
    }
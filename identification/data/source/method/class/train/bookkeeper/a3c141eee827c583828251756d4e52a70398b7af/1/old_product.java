private long flushSnapshot(final SkipListFlusher flusher, Checkpoint checkpoint) throws IOException {
        long size = 0;
        if (this.snapshot.compareTo(checkpoint) < 0) {
            long ledger, ledgerGC = -1;
            synchronized (this) {
                EntrySkipList keyValues = this.snapshot;
                if (keyValues.compareTo(checkpoint) < 0) {
                    for (EntryKey key : keyValues.keySet()) {
                        EntryKeyValue kv = (EntryKeyValue) key;
                        size += kv.getLength();
                        ledger = kv.getLedgerId();
                        if (ledgerGC != ledger) {
                            try {
                                flusher.process(ledger, kv.getEntryId(), kv.getValueAsByteBuffer());
                            } catch (NoLedgerException exception) {
                                ledgerGC = ledger;
                            }
                        }
                    }
                    flushBytesCounter.add(size);
                    clearSnapshot(keyValues);
                }
            }
        }

        return size;
    }
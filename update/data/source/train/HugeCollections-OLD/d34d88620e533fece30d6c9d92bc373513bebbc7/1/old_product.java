V put(final DirectBytes keyBytes, final V value, int hash2, boolean replaceIfPresent) {
            lock();
            try {
                hash2 = hashLookup.startSearch(hash2);
                while (true) {
                    final int pos = hashLookup.nextPos();
                    if (pos < 0) {
                        putEntry(keyBytes, value, hash2);

                        return null;

                    } else {
                        final long offset = entriesOffset + pos * entrySize;
                        tmpBytes.storePositionAndSize(bytes, offset, entrySize);
                        if (!keyEquals(keyBytes, tmpBytes))
                            continue;
                        final long keyLength = keyBytes.remaining();
                        tmpBytes.skip(keyLength);
                        final long alignPosition = align(tmpBytes.position());
                        tmpBytes.position(alignPosition);
                        if (replaceIfPresent) {
                            if (putReturnsNull) {
                                appendInstance(keyBytes, value);
                                return null;
                            }
                            final V v = readObjectUsing(null, offset + alignPosition);
                            tmpBytes.position(alignPosition);
                            appendInstance(keyBytes, value);
                            return v;

                        } else {
                            if (putReturnsNull) {
                                return null;
                            }

                            return readObjectUsing(null, offset + keyLength);
                        }
                    }
                }
            } finally {
                unlock();
            }
        }
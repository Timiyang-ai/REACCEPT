@Override
    public boolean remove(int hash, int value) {
        if (hash == UNSET_KEY)
            hash = HASH_INSTEAD_OF_UNSET_KEY;
        int pos = (hash & capacityMask) << 3; // 8 bytes per entry
        int pos0 = -1;
        // find the end of the chain.
        boolean found = false;
        for (int i = 0; i <= capacityMask; i++) {
            long entry = bytes.readLong(pos);
//            int hash2 = bytes.readInt(pos + KEY);
            int hash2 = (int) (entry >> 32);
            if (hash2 == hash) {
//                int value2 = bytes.readInt(pos + VALUE);
                int value2 = (int) entry;
                if (value2 == value) {
                    found = true;
                    pos0 = pos;
                }
            } else if (hash2 == UNSET_KEY) {
                break;
            }
            pos = (pos + ENTRY_SIZE) & capacityMask2;
        }
        if (!found)
            return false;
        int pos2 = pos;
        // now work back up the chain from pos to pos0;
        // Note: because of the mask, the pos can be actually less than pos0,
        // thus using != operator instead of >=
        while (pos != pos0) {
            pos = (pos - ENTRY_SIZE) & capacityMask2;
            long entry = bytes.readLong(pos);
//            int hash2 = bytes.readInt(pos + KEY);
            int hash2 = (int) (entry >> 32);
            if (hash2 == hash) {
                // swap values and zeroOut
                if (pos != pos0) {
                    long entry2 = bytes.readLong(pos);
                    bytes.writeLong(pos0, entry2);
                }
                bytes.writeLong(pos, UNSET_ENTRY);
                break;
            }
        }
        pos = (pos + ENTRY_SIZE) & capacityMask2;
        // re-inset any values in between pos and pos2.
        while (pos < pos2) {
            long entry2 = bytes.readLong(pos);
            int hash2 = (int) (entry2 >> 32);
            int value2 = (int) entry2;
            // zeroOut the entry
            bytes.writeLong(pos, UNSET_ENTRY);
            // this might put it back in the same place or a different one.
            put(hash2, value2);
            pos = (pos + ENTRY_SIZE) & capacityMask2;
        }
        return true;
    }
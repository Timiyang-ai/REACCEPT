@Override
    public void put(int hash, int value) {
        if (hash == UNSET_KEY)
            hash = HASH_INSTEAD_OF_UNSET_KEY;
        int pos = (hash & capacityMask) << 3; // 8 bytes per entry
        for (int i = 0; i <= capacityMask; i++) {
            long entry = bytes.readLong(pos);
            int hash2 = (int) (entry >> 32);
            if (hash2 == UNSET_KEY) {
                bytes.writeLong(pos, (((long) hash) << 32) | (value & 0xFFFFFFFFL));
                return;
            }
            if (hash2 == hash) {
                int value2 = (int) entry;
                if (value2 == value)
                    return;
            }
            pos = (pos + ENTRY_SIZE) & capacityMask2;
        }
        throw new IllegalStateException("IntIntMultiMap is full");
    }
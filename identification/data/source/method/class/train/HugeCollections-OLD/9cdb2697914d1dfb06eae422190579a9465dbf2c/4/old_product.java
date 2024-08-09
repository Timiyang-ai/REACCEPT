public void put(int key, int value) {
        if (key == unsetKey())
            throw new IllegalArgumentException("Cannot add a key with unset value " + unsetKey());
        int pos = (key & capacityMask) << 3; // 8 bytes per entry
        for (int i = 0; i <= capacityMask; i++) {
            long entry = bytes.readLong(pos);
//            int key2 = bytes.readInt(pos + KEY);
            int key2 = (int) (entry >> 32);
            if (key2 == unsetKey()) {
                bytes.writeLong(pos, ((long) key << 32) | (value & 0xFFFFFFFFL));
                size++;
                return;
            }
            if (key2 == key) {
//                int value2 = bytes.readInt(pos + VALUE);
                int value2 = (int) entry;
                if (value2 == value)
                    return;
            }
            pos = (pos + ENTRY_SIZE) & capacityMask2;
        }
        throw new IllegalStateException("IntIntMultiMap is full");
    }
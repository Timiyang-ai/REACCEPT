public void put(int key, int value) {
        if (key == unsetKey())
            throw new IllegalArgumentException("Cannot add a key with unset value " + unsetKey());
        int pos = (key & capacityMask) << 3; // 8 bytes per entry
        for (int i = 0; i <= capacityMask; i++) {
            int key2 = bytes.readInt(pos + KEY);
            if (key2 == unsetKey()) {
                bytes.writeInt(pos + KEY, key);
                bytes.writeInt(pos + VALUE, value);
                size++;
                return;
            }
            if (key2 == key) {
                int value2 = bytes.readInt(pos + VALUE);
                if (value2 == value)
                    return;
            }
            pos = (pos + ENTRY_SIZE) & capacityMask2;
        }
        throw new IllegalStateException("IntIntMultiMap is full");
    }
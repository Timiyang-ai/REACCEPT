public boolean remove(int key, int value) {
        if (key == unsetKey())
            throw new IllegalArgumentException("Cannot remove a key with unset value " + unsetKey());
        int pos = (key & capacityMask) << 3; // 8 bytes per entry
        int pos0 = -1;
        // find the end of the chain.
        boolean found = false;
        for (int i = 0; i <= capacityMask; i++) {
            int key2 = bytes.readInt(pos + KEY);
            if (key2 == key) {
                int value2 = bytes.readInt(pos + VALUE);
                if (value2 == value) {
                    found = true;
                    pos0 = pos;
                }
            } else if (key2 == unsetKey()) {
                break;
            }
            pos = (pos + ENTRY_SIZE) & capacityMask2;
        }
        if (!found)
            return false;
        size--;
        int pos2 = pos;
        // now work back up the chain from pos to pos0;
        // Note: because of the mask, the pos can be actually less than pos0, thus using != operator instead of >=
        while (pos != pos0) {
            pos = (pos - ENTRY_SIZE) & capacityMask2;
            int key2 = bytes.readInt(pos + KEY);
            if (key2 == key) {
                // swap values and zeroOut
                if (pos != pos0) {
                    int value2 = bytes.readInt(pos + VALUE);
                    bytes.writeInt(pos0 + VALUE, value2);
                }
                bytes.writeInt(pos, unsetKey());
                bytes.writeInt(pos + 4, unsetValue());
                break;
            }
        }
        pos = (pos + ENTRY_SIZE) & capacityMask2;
        // re-inset any values in between pos and pos2.
        while (pos < pos2) {
            int key2 = bytes.readInt(pos + KEY);
            int value2 = bytes.readInt(pos + VALUE);
            // zeroOut the entry
            bytes.writeInt(pos, unsetKey());
            bytes.writeInt(pos + 4, unsetValue());
            size--;
            // this might put it back in the same place or a different one.
            put(key2, value2);
            pos = (pos + ENTRY_SIZE) & capacityMask2;
        }
        return true;
    }
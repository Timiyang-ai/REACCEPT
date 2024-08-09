public T get(int key) {
        if (key < 0) {
            throw new IllegalArgumentException("Key cannot be negative: " + key);
        }
        int probeCount = 0;
        int firstProbe = firstProbe(key);
        int slot = firstProbe;
        while (true) {
            final int t = keys[slot];
            if (t == EMPTY) {
                return null;
            }
            if (t == DELETED) {
                slot = nextProbe(firstProbe, ++probeCount);
                continue;
            }
            if (t == key) {
                return values[slot];
            }
            slot = nextProbe(firstProbe, ++probeCount);
        }
    }
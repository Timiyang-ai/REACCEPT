public int get(T key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");
        int probeCount = 0;
        int slot = firstProbe(hash(key));
        while (true) {
            final T t = keys[slot];
            if (t == null) {
                return 0;
            }
            if (t == TOMB_STONE) {
                slot = nextProbe(slot, ++probeCount);
                continue;
            }
            if (t.equals(key)) {
                return values[slot];
            }
            slot = nextProbe(slot, ++probeCount);
        }
    }
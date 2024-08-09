public int get(T key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");
        int probeCount = 0;
        int firstProbe = firstProbe(hash(key));
        int slot = firstProbe;
        while (true) {
            final T t = keys[slot];
            if (t == null) {
                return 0;
            }
            if (t == TOMB_STONE) {
                slot = nextProbe(firstProbe, ++probeCount);
                continue;
            }
            if (t.equals(key)) {
                return values[slot];
            }
            slot = nextProbe(firstProbe, ++probeCount);
        }
    }
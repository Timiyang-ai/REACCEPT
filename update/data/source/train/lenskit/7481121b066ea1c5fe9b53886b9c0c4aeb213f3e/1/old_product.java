public Iterator<VectorEntry> fastIterator(VectorEntry.State state) {
        IntIterator iter;
        switch (state) {
        case SET:
            iter = new BitSetIterator(usedKeys, 0, domainSize);
            break;
        case UNSET: {
            BitSet unused = (BitSet) usedKeys.clone();
            unused.flip(0, domainSize);
            iter = new BitSetIterator(unused, 0, domainSize);
            break;
        }
        case EITHER: {
            iter = new IntIntervalList(0, domainSize).iterator();
            break;
        }
        default: // should be impossible
            throw new IllegalArgumentException("invalid entry state");
        }
        return new FastIterImpl(iter);
    }
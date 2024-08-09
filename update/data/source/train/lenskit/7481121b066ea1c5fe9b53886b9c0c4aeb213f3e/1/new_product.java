public Iterator<VectorEntry> fastIterator(VectorEntry.State state) {
        IntIterator iter;
        switch (state) {
        case SET:
            iter = keys.activeIndexIterator();
            break;
        case UNSET: {
            iter = keys.clone().invert().activeIndexIterator();
            break;
        }
        case EITHER: {
            iter = IntIterators.fromTo(keys.getStartIndex(), keys.getEndIndex());
            break;
        }
        default: // should be impossible
            throw new IllegalArgumentException("invalid entry state");
        }
        return new FastIterImpl(iter);
    }
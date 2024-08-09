public long add(T item) {
        final long nextSequence = ringbuffer.peekNextTailSequence();
        if (store.isEnabled()) {
            try {
                store.store(nextSequence, convertToData(item));
            } catch (Exception e) {
                throw new HazelcastException(e);
            }
        }

        final long storedSequence = addInternal(item);
        if (storedSequence != nextSequence) {
            throw new IllegalStateException("Sequence we stored the item with and Ringbuffer sequence differs. Was the "
                    + "Ringbuffer mutated from multiple threads?");
        }

        return storedSequence;
    }
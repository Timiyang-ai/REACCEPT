public long add(T item) {
        final long sequence = addInternal(item);
        if (store.isEnabled()) {
            try {
                store.store(sequence, convertToData(item));
            } catch (Exception e) {
                throw new HazelcastException(e);
            }
        }
        return sequence;
    }
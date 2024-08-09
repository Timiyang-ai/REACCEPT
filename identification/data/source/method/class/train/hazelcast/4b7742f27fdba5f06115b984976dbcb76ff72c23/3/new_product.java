public long add(Data item) {
        final long sequence = addInternal(item);
        if (store.isEnabled()) {
            try {
                store.store(sequence, item);
            } catch (Exception e) {
                throw new HazelcastException(e);
            }
        }
        return sequence;
    }
@Override
    public final String toString() {
        if (!initialized) {
            return null;
        }
        return spiImpl.engineToString();
    }
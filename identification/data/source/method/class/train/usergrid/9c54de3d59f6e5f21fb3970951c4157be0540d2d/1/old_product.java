public boolean isCompactionPending() {
        return !isTooSmallToCompact();
    }
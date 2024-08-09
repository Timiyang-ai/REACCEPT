default boolean isClosed() {
        return !getCurrentTrade().isOpened();
    }
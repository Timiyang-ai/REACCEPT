public boolean isWatching() {
        BasicKeyChain.State basicState = basic.isWatching();
        BasicKeyChain.State activeState = BasicKeyChain.State.EMPTY;
        if (chains != null && !chains.isEmpty()) {
            if (getActiveKeyChain().isWatching())
                activeState = BasicKeyChain.State.WATCHING;
            else
                activeState = BasicKeyChain.State.REGULAR;
        }
        if (basicState == BasicKeyChain.State.EMPTY) {
            if (activeState == BasicKeyChain.State.EMPTY)
                throw new IllegalStateException("Empty key chain group: cannot answer isWatching() query");
            return activeState == BasicKeyChain.State.WATCHING;
        } else if (activeState == BasicKeyChain.State.EMPTY)
            return basicState == BasicKeyChain.State.WATCHING;
        else {
            if (activeState != basicState)
                throw new IllegalStateException("Mix of watching and non-watching keys in wallet");
            return activeState == BasicKeyChain.State.WATCHING;
        }
    }
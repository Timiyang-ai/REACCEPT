public boolean isWatching() {
        Boolean basicChainIsWatching = basic.isWatching();
        Boolean deterministicChainIsWatching = !chains.isEmpty() ? getActiveKeyChain().isWatching() : null;
        if (basicChainIsWatching == null && deterministicChainIsWatching == null)
            throw new IllegalStateException("No keys");
        if (basicChainIsWatching == null && deterministicChainIsWatching != null)
            return deterministicChainIsWatching;
        if (basicChainIsWatching != null && deterministicChainIsWatching == null)
            return basicChainIsWatching;
        if (basicChainIsWatching == deterministicChainIsWatching)
            return deterministicChainIsWatching;
        if (basicChainIsWatching && !deterministicChainIsWatching)
            throw new IllegalStateException("Basic chain is watching, deterministic chain is not");
        else
            throw new IllegalStateException("Basic chain is not watching, deterministic chain is");
    }
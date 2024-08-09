public void addFollowingAccountKeys(List<DeterministicKey> followingAccountKeys) {
        checkState(!isMarried(), "KeyChainGroup is married already");
        checkState(getActiveKeyChain().numLeafKeysIssued() == 0, "Active keychain already has keys in use");
        DeterministicKey accountKey = getActiveKeyChain().getWatchingKey();
        for (DeterministicKey key : followingAccountKeys) {
            checkArgument(key.getPath().size() == 1, "Following keys have to be account keys");
            DeterministicKeyChain chain = DeterministicKeyChain.watchAndFollow(key);
            if (lookaheadSize > 0) {
                chain.setLookaheadSize(lookaheadSize);
            }
            followingKeychains.put(accountKey, chain);
        }
    }
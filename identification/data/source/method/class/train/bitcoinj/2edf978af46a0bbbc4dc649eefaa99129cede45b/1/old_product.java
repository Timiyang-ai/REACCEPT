public void addFollowingAccounts(List<DeterministicKey> followingAccountKeys) {
        if (isMarried()) {
            throw new IllegalStateException("KeyChainGroup is married already");
        }

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
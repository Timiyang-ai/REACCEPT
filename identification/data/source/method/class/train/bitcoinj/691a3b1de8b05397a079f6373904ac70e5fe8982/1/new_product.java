@Deprecated
    public static Wallet fromKeys(NetworkParameters params, List<ECKey> keys) {
        for (ECKey key : keys)
            checkArgument(!(key instanceof DeterministicKey));

        KeyChainGroup group = KeyChainGroup.builder(params).build();
        group.importKeys(keys);
        return new Wallet(params, group);
    }
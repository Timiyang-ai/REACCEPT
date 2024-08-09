public Address freshAddress(KeyChain.KeyPurpose purpose) {
        DeterministicKeyChain chain = getActiveKeyChain();
        DeterministicKey key = chain.getKey(purpose);
        if (isMarried(chain)) {
            List<ECKey> keys = ImmutableList.<ECKey>builder()
                    .addAll(getFollowingKeys(purpose, chain.getWatchingKey()))
                    .add(key).build();
            Address freshAddress = Address.fromP2SHScript(params, ScriptBuilder.createP2SHOutputScript(2, keys));
            currentAddresses.put(purpose, freshAddress);
            return freshAddress;
        } else {
            return freshKey(purpose).toAddress(params);
        }
    }
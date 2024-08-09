public Address freshAddress(KeyChain.KeyPurpose purpose) {
        DeterministicKeyChain chain = getActiveKeyChain();
        if (isMarried(chain)) {
            List<ECKey> marriedKeys = freshMarriedKeys(purpose, chain);
            Script p2shScript = makeP2SHOutputScript(marriedKeys);
            Address freshAddress = Address.fromP2SHScript(params, p2shScript);
            maybeLookaheadScripts();
            currentAddresses.put(purpose, freshAddress);
            return freshAddress;
        } else {
            return freshKey(purpose).toAddress(params);
        }
    }
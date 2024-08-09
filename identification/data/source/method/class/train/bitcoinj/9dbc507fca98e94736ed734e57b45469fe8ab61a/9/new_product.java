public Address freshAddress(KeyChain.KeyPurpose purpose) {
        DeterministicKeyChain chain = getActiveKeyChain();
        if (isMarried(chain)) {
            List<ECKey> marriedKeys = freshMarriedKeys(purpose, chain);
            Address freshAddress = Address.fromP2SHScript(params, ScriptBuilder.createP2SHOutputScript(2, marriedKeys));
            currentAddresses.put(purpose, freshAddress);
            return freshAddress;
        } else {
            return freshKey(purpose).toAddress(params);
        }
    }
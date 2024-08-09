public Address freshAddress(KeyChain.KeyPurpose purpose) {
        DeterministicKeyChain chain = getActiveKeyChain();
        if (chain.isMarried()) {
            Script outputScript = chain.freshOutputScript(purpose);
            checkState(outputScript.isPayToScriptHash()); // Only handle P2SH for now
            Address freshAddress = Address.fromP2SHScript(params, outputScript);
            maybeLookaheadScripts();
            currentAddresses.put(purpose, freshAddress);
            return freshAddress;
        } else {
            return freshKey(purpose).toAddress(params);
        }
    }
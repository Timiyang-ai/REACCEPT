public Address freshAddress(KeyChain.KeyPurpose purpose) {
        DeterministicKeyChain chain = getActiveKeyChain();
        if (chain.isMarried()) {
            Script outputScript = chain.freshOutputScript(purpose);
            checkState(ScriptPattern.isPayToScriptHash(outputScript)); // Only handle P2SH for now
            Address freshAddress = LegacyAddress.fromScriptHash(params,
                    ScriptPattern.extractHashFromPayToScriptHash(outputScript));
            maybeLookaheadScripts();
            currentAddresses.put(purpose, freshAddress);
            return freshAddress;
        } else {
            return LegacyAddress.fromKey(params, freshKey(purpose));
        }
    }
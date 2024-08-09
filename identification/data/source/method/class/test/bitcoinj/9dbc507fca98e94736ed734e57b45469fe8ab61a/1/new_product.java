public Address freshAddress(KeyChain.KeyPurpose purpose) {
        DeterministicKeyChain chain = getActiveKeyChain();
        Script.ScriptType outputScriptType = chain.getOutputScriptType();
        if (chain.isMarried()) {
            Script outputScript = chain.freshOutputScript(purpose);
            checkState(ScriptPattern.isPayToScriptHash(outputScript)); // Only handle P2SH for now
            Address freshAddress = LegacyAddress.fromScriptHash(params,
                    ScriptPattern.extractHashFromPayToScriptHash(outputScript));
            maybeLookaheadScripts();
            currentAddresses.put(purpose, freshAddress);
            return freshAddress;
        } else if (outputScriptType == Script.ScriptType.P2PKH || outputScriptType == Script.ScriptType.P2WPKH) {
            return Address.fromKey(params, freshKey(purpose), outputScriptType);
        } else {
            throw new IllegalStateException(chain.getOutputScriptType().toString());
        }
    }
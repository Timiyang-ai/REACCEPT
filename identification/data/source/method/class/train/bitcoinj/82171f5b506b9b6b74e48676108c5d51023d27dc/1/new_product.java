public Address getToAddress(NetworkParameters params, boolean forcePayToPubKey) throws ScriptException {
        if (ScriptPattern.isPayToPubKeyHash(this))
            return LegacyAddress.fromPubKeyHash(params, ScriptPattern.extractHashFromPayToPubKeyHash(this));
        else if (ScriptPattern.isPayToScriptHash(this))
            return LegacyAddress.fromScriptHash(params, ScriptPattern.extractHashFromPayToScriptHash(this));
        else if (forcePayToPubKey && ScriptPattern.isPayToPubKey(this))
            return LegacyAddress.fromKey(params, ECKey.fromPublicOnly(ScriptPattern.extractKeyFromPayToPubKey(this)));
        else if (ScriptPattern.isPayToWitnessHash(this))
            return SegwitAddress.fromHash(params, ScriptPattern.extractHashFromPayToWitnessHash(this));
        else
            throw new ScriptException(ScriptError.SCRIPT_ERR_UNKNOWN_ERROR, "Cannot cast this script to a pay-to-address type");
    }
public Address getToAddress(NetworkParameters params, boolean forcePayToPubKey) throws ScriptException {
        if (ScriptPattern.isP2PKH(this))
            return LegacyAddress.fromPubKeyHash(params, ScriptPattern.extractHashFromP2PKH(this));
        else if (ScriptPattern.isP2SH(this))
            return LegacyAddress.fromScriptHash(params, ScriptPattern.extractHashFromP2SH(this));
        else if (forcePayToPubKey && ScriptPattern.isP2PK(this))
            return LegacyAddress.fromKey(params, ECKey.fromPublicOnly(ScriptPattern.extractKeyFromP2PK(this)));
        else if (ScriptPattern.isP2WH(this))
            return SegwitAddress.fromHash(params, ScriptPattern.extractHashFromP2WH(this));
        else
            throw new ScriptException(ScriptError.SCRIPT_ERR_UNKNOWN_ERROR, "Cannot cast this script to an address");
    }
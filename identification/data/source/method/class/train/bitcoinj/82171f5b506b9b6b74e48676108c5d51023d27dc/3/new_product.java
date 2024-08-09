public Address getToAddress(NetworkParameters params, boolean forcePayToPubKey) throws ScriptException {
        if (ScriptPattern.isPayToPubKeyHash(this))
            return new Address(params, ScriptPattern.extractHashFromPayToPubKeyHash(this));
        else if (ScriptPattern.isPayToScriptHash(this))
            return Address.fromP2SHScript(params, this);
        else if (forcePayToPubKey && ScriptPattern.isPayToPubKey(this))
            return Address.fromKey(params, ECKey.fromPublicOnly(getPubKey()));
        else
            throw new ScriptException(ScriptError.SCRIPT_ERR_UNKNOWN_ERROR, "Cannot cast this script to a pay-to-address type");
    }
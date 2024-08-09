public Address getToAddress(NetworkParameters params, boolean forcePayToPubKey) throws ScriptException {
        if (isSentToAddress())
            return new Address(params, getPubKeyHash());
        else if (isPayToScriptHash())
            return Address.fromP2SHScript(params, this);
        else if (forcePayToPubKey && isSentToRawPubKey())
            return ECKey.fromPublicOnly(getPubKey()).toAddress(params);
        else
            throw new ScriptException(ScriptError.SCRIPT_ERR_UNKNOWN_ERROR, "Cannot cast this script to a pay-to-address type");
    }
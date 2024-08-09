public Address getToAddress(NetworkParameters params) throws ScriptException {
        if (isSentToAddress())
            return new Address(params, getPubKeyHash());
        else if (isPayToScriptHash())
            return Address.fromP2SHScript(params, this);
        else
            throw new ScriptException("Cannot cast this script to a pay-to-address type");
    }
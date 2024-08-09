public Address getToAddress(NetworkParameters params) throws ScriptException {
        return new Address(params, getPubKeyHash());
    }
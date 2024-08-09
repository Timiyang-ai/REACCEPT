public Address getToAddress() throws ScriptException {
        return new Address(params, getPubKeyHash());
    }
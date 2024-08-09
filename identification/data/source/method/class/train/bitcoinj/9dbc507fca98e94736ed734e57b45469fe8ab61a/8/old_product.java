public Address freshAddress(KeyChain.KeyPurpose purpose, NetworkParameters params) {
        return freshKey(purpose).toAddress(params);
    }
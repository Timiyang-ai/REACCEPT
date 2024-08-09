public byte[] addAccountWithPrivateKey(byte[] privateKeyBytes) {
        Account account = createAccount(ECKey.fromPrivate(privateKeyBytes));

        saveAccountToMemory(account);

        return account.getAddress();
    }
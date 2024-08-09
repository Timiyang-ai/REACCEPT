public byte[] addAccountWithPrivateKey(byte[] privateKeyBytes) {
        Account account = createAccount(ECKey.fromPrivate(privateKeyBytes));

        saveAccountInformationIntoMemory(account, null);

        return account.getAddress();
    }
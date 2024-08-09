public static TransactionContract revokeContract(Contract contract, PrivateKey... key) throws IOException {

        report("keys num: " + key.length);

        TransactionContract tc = new TransactionContract();
        tc.setIssuer(key);
        tc.addContractToRemove(contract);

        tc.seal();

        registerContract(tc, true);

        return tc;
    }
public static Contract revokeContract(Contract contract, PrivateKey... key) throws IOException {

        report("keys num: " + key.length);

        Contract tc = ContractsService.createRevocation(contract, key);
        registerContract(tc, 0,true);

        return tc;
    }
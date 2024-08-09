public static Parcel revokeContract(Contract contract, Contract tu, int amount, Set<PrivateKey> tuKeys, boolean withTestPayment, PrivateKey... key) throws IOException {

        report("keys num: " + key.length);

        Contract tc = ContractsService.createRevocation(contract, key);
        Parcel parcel = registerContract(tc, tu, amount, tuKeys, withTestPayment, 0);

        return parcel;
    }
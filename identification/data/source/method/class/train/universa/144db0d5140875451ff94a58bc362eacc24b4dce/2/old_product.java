public static Parcel revokeContract(Contract contract, Contract tu, int amount, Set<PrivateKey> tuKeys, boolean withTestPayment, PrivateKey... key) throws IOException {

        report("keys num: " + key.length);

        Contract tc = ContractsService.createRevocation(contract, key);
        Parcel parcel = prepareForRegisterContract(tc, tu, amount, tuKeys, withTestPayment);
        if (parcel != null)
            registerParcel(parcel, 0);

        return parcel;
    }
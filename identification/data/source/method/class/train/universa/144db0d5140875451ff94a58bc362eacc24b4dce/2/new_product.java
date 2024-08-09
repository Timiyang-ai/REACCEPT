public static Parcel revokeContract(Contract contract, Contract u, int amount, Set<PrivateKey> uKeys, boolean withTestPayment, PrivateKey... key) throws IOException {

        report("keys num: " + key.length);

        Contract tc = ContractsService.createRevocation(contract, key);
        Parcel parcel = prepareForRegisterContract(tc, u, amount, uKeys, withTestPayment);
        if (parcel != null)
            registerParcel(parcel, 0);

        return parcel;
    }
public static PendingFederation deserializePendingFederation(byte[] data) {
        return new PendingFederation(deserializeBtcPublicKeys(data));
    }
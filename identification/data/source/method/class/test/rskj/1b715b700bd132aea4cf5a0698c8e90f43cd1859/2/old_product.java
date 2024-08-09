public static byte[] serializePendingFederation(PendingFederation pendingFederation) {
        return serializeBtcPublicKeys(pendingFederation.getBtcPublicKeys());
    }
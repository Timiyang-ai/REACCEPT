public static byte[] serializePendingFederation(PendingFederation pendingFederation) {
        return serializePublicKeys(pendingFederation.getPublicKeys());
    }
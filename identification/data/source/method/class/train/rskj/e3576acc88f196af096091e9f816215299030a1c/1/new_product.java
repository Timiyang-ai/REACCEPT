public static byte[] serializeFederation(Federation federation) {
        return serializeFederationWithSerializer(federation,
                BridgeSerializationUtils::serializeFederationMember);
    }
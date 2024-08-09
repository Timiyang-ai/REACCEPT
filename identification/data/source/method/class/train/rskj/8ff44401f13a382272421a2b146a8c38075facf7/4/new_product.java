public static Federation deserializeFederation(byte[] data, NetworkParameters networkParameters) {
        return deserializeFederationWithDesserializer(data, networkParameters,
                BridgeSerializationUtils::deserializeFederationMember);
    }
public ExtendedMetadata generateExtendedMetadata() {

        ExtendedMetadata metadata;

        if (extendedMetadata != null) {
            metadata = extendedMetadata.clone();
        } else {
            metadata = new ExtendedMetadata();
        }

        metadata.setIdpDiscoveryEnabled(isIncludeDiscovery());

        if (isIncludeDiscovery()) {
            if (metadata.getIdpDiscoveryURL() == null) {
                metadata.setIdpDiscoveryURL(getServerURL(entityBaseURL, entityAlias, getSAMLDiscoveryPath()));
            }
            if (metadata.getIdpDiscoveryResponseURL() == null) {
                if (customDiscoveryURL != null) {
                    metadata.setIdpDiscoveryURL(customDiscoveryURL);
                } else {
                    metadata.setIdpDiscoveryResponseURL(getServerURL(entityBaseURL, entityAlias, getSAMLEntryPointPath()) + "?" + SAMLEntryPoint.DISCOVERY_RESPONSE_PARAMETER + "=true");
                }
            }
        } else {
            metadata.setIdpDiscoveryURL(null);
            metadata.setIdpDiscoveryResponseURL(null);
        }

        metadata.setEncryptionKey(encryptionKey);
        metadata.setSigningKey(signingKey);
        metadata.setAlias(entityAlias);
        metadata.setTlsKey(tlsKey);
        metadata.setLocal(true);

        return metadata;

    }
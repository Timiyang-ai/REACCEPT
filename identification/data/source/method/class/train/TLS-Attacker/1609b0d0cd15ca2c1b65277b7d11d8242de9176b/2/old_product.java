public static ExtensionHandler getExtensionHandler(TlsContext context, ExtensionType type) {
        try {
            switch (type) {
                case ALPN:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
                case CACHED_INFO:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
                case CERT_TYPE:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
                case CLIENT_AUTHZ:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
                case CLIENT_CERTIFICATE_TYPE:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
                case CLIENT_CERTIFICATE_URL:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
                case EC_POINT_FORMATS:
                    return new ECPointFormatExtensionHandler(context);
                case ELLIPTIC_CURVES:
                    return new EllipticCurvesExtensionHandler(context);
                case ENCRYPT_THEN_MAC:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
                case EXTENDED_MASTER_SECRET:
                    return new ExtendedMasterSecretExtensionHandler(context);
                case HEARTBEAT:
                    return new HeartbeatExtensionHandler(context);
                case KEY_SHARE:
                    return new KeyShareExtensionHandler(context);
                case MAX_FRAGMENT_LENGTH:
                    return new MaxFragmentLengthExtensionHandler(context);
                case PADDING:
                    return new PaddingExtensionHandler(context);
                case RENEGOTIATION_INFO:
                    return new RenegotiationInfoExtensionHandler(context);
                case SERVER_AUTHZ:
                    return new ServerNameIndicationExtensionHandler(context);
                case SERVER_CERTIFICATE_TYPE:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
                case SERVER_NAME_INDICATION:
                    return new ServerNameIndicationExtensionHandler(context);
                case SESSION_TICKET:
                    return new SessionTicketTLSExtensionHandler(context);
                case SIGNATURE_AND_HASH_ALGORITHMS:
                    return new SignatureAndHashAlgorithmsExtensionHandler(context);
                case SIGNED_CERTIFICATE_TIMESTAMP:
                    return new SignedCertificateTimestampExtensionHandler(context);
                case SRP:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
                case STATUS_REQUEST:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
                case STATUS_REQUEST_V2:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
                case SUPPORTED_VERSIONS:
                    return new SupportedVersionsExtensionHandler(context);
                case TOKEN_BINDING:
                    return new TokenBindingExtensionHandler(context);
                case TRUNCATED_HMAC:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
                case TRUSTED_CA_KEYS:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
                case UNKNOWN:
                    return new UnknownExtensionHandler(context);
                case USER_MAPPING:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
                case USE_SRTP:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
                default:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
            }

        } catch (UnsupportedOperationException E) {
            LOGGER.debug("Could not retrieve correct Handler, returning UnknownExtensionHandler", E);
        }
        return new UnknownExtensionHandler(context);
    }
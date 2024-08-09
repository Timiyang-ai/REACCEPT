public static ExtensionHandler getExtensionHandler(TlsContext context, ExtensionType type,
            HandshakeMessageType handshakeMessageType) {
        try {
            switch (type) {
                case ALPN:
                    return new AlpnExtensionHandler(context);
                case CACHED_INFO:
                    return new CachedInfoExtensionHandler(context);
                case CERT_TYPE:
                    return new CertificateTypeExtensionHandler(context);
                case CLIENT_AUTHZ:
                    return new ClientAuthzExtensionHandler(context);
                case CLIENT_CERTIFICATE_TYPE:
                    return new ClientCertificateTypeExtensionHandler(context);
                case CLIENT_CERTIFICATE_URL:
                    return new ClientCertificateUrlExtensionHandler(context);
                case EARLY_DATA:
                    return new EarlyDataExtensionHandler(context);
                case EC_POINT_FORMATS:
                    return new EcPointFormatExtensionHandler(context);
                case ELLIPTIC_CURVES:
                    return new EllipticCurvesExtensionHandler(context);
                case ENCRYPT_THEN_MAC:
                    return new EncryptThenMacExtensionHandler(context);
                case EXTENDED_MASTER_SECRET:
                    return new ExtendedMasterSecretExtensionHandler(context);
                case HEARTBEAT:
                    return new HeartbeatExtensionHandler(context);
                case KEY_SHARE:
                    if (handshakeMessageType == HandshakeMessageType.HELLO_RETRY_REQUEST) {
                        return new HrrKeyShareExtensionHandler(context);
                    }
                    return new KeyShareExtensionHandler(context);
                case MAX_FRAGMENT_LENGTH:
                    return new MaxFragmentLengthExtensionHandler(context);
                case PADDING:
                    return new PaddingExtensionHandler(context);
                case PRE_SHARED_KEY:
                    return new PreSharedKeyExtensionHandler(context);
                case PSK_KEY_EXCHANGE_MODES:
                    return new PSKKeyExchangeModesExtensionHandler(context);
                case RENEGOTIATION_INFO:
                    return new RenegotiationInfoExtensionHandler(context);
                case SERVER_AUTHZ:
                    return new ServerAuthzExtensionHandler(context);
                case SERVER_CERTIFICATE_TYPE:
                    return new ServerCertificateTypeExtensionHandler(context);
                case SERVER_NAME_INDICATION:
                    return new ServerNameIndicationExtensionHandler(context);
                case SESSION_TICKET:
                    return new SessionTicketTlsExtensionHandler(context);
                case SIGNATURE_AND_HASH_ALGORITHMS:
                    return new SignatureAndHashAlgorithmsExtensionHandler(context);
                case SIGNED_CERTIFICATE_TIMESTAMP:
                    return new SignedCertificateTimestampExtensionHandler(context);
                case SRP:
                    return new SrpExtensionHandler(context);
                case STATUS_REQUEST:
                    return new CertificateStatusRequestExtensionHandler(context);
                case STATUS_REQUEST_V2:
                    return new CertificateStatusRequestV2ExtensionHandler(context);
                case SUPPORTED_VERSIONS:
                    return new SupportedVersionsExtensionHandler(context);
                case TOKEN_BINDING:
                    return new TokenBindingExtensionHandler(context);
                case TRUNCATED_HMAC:
                    return new TruncatedHmacExtensionHandler(context);
                case TRUSTED_CA_KEYS:
                    return new TrustedCaIndicationExtensionHandler(context);
                case UNKNOWN:
                    return new UnknownExtensionHandler(context);
                case USER_MAPPING:
                    return new UserMappingExtensionHandler(context);
                case USE_SRTP:
                    return new SrtpExtensionHandler(context);
                default:
                    throw new UnsupportedOperationException(type.name() + " Extension are not supported yet");
            }

        } catch (UnsupportedOperationException E) {
            LOGGER.debug("Could not retrieve correct Handler, returning UnknownExtensionHandler", E);
        }
        return new UnknownExtensionHandler(context);
    }
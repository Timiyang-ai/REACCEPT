    @Test
    public void getExtensionHandler() {
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.ALPN, null) instanceof AlpnExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.CACHED_INFO, null) instanceof CachedInfoExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.CERT_TYPE, null) instanceof CertificateTypeExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.CLIENT_AUTHZ, null) instanceof ClientAuthzExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.CLIENT_CERTIFICATE_TYPE, null) instanceof ClientCertificateTypeExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.CLIENT_CERTIFICATE_URL, null) instanceof ClientCertificateUrlExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.EARLY_DATA, null) instanceof EarlyDataExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.EC_POINT_FORMATS, null) instanceof EcPointFormatExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.ELLIPTIC_CURVES, null) instanceof EllipticCurvesExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.ENCRYPT_THEN_MAC, null) instanceof EncryptThenMacExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.EXTENDED_MASTER_SECRET, null) instanceof ExtendedMasterSecretExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.HEARTBEAT, null) instanceof HeartbeatExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.KEY_SHARE_OLD,
                HandshakeMessageType.HELLO_RETRY_REQUEST) instanceof HrrKeyShareExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.KEY_SHARE,
                HandshakeMessageType.HELLO_RETRY_REQUEST) instanceof HrrKeyShareExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.KEY_SHARE_OLD, null) instanceof KeyShareExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.KEY_SHARE, null) instanceof KeyShareExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.MAX_FRAGMENT_LENGTH, null) instanceof MaxFragmentLengthExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.PADDING, null) instanceof PaddingExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.PRE_SHARED_KEY, null) instanceof PreSharedKeyExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.PSK_KEY_EXCHANGE_MODES, null) instanceof PSKKeyExchangeModesExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.RENEGOTIATION_INFO, null) instanceof RenegotiationInfoExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.SERVER_AUTHZ, null) instanceof ServerAuthzExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.SERVER_CERTIFICATE_TYPE, null) instanceof ServerCertificateTypeExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.SERVER_NAME_INDICATION, null) instanceof ServerNameIndicationExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.SESSION_TICKET, null) instanceof SessionTicketTlsExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.SIGNATURE_AND_HASH_ALGORITHMS, null) instanceof SignatureAndHashAlgorithmsExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.SIGNED_CERTIFICATE_TIMESTAMP, null) instanceof SignedCertificateTimestampExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.SRP, null) instanceof SrpExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.STATUS_REQUEST, null) instanceof CertificateStatusRequestExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.STATUS_REQUEST_V2, null) instanceof CertificateStatusRequestV2ExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.SUPPORTED_VERSIONS, null) instanceof SupportedVersionsExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.TOKEN_BINDING, null) instanceof TokenBindingExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.TRUNCATED_HMAC, null) instanceof TruncatedHmacExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.TRUSTED_CA_KEYS, null) instanceof TrustedCaIndicationExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.UNKNOWN, null) instanceof UnknownExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.USER_MAPPING, null) instanceof UserMappingExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.USE_SRTP, null) instanceof SrtpExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.PWD_PROTECT, null) instanceof PWDProtectExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.PWD_CLEAR, null) instanceof PWDClearExtensionHandler);
        assertTrue(HandlerFactory.getExtensionHandler(context, ExtensionType.PASSWORD_SALT, null) instanceof PasswordSaltExtensionHandler);
    }
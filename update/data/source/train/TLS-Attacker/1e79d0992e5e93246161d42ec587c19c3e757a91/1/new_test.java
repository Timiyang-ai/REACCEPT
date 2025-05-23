@Test
    public void testAdjustTLSContext() {
        context.setTalkingConnectionEndType(ConnectionEndType.CLIENT);
        CertificateMessage message = new CertificateMessage();
        message.setX509CertificateBytes(ArrayConverter
                .hexStringToByteArray("00023a30820236308201dba0030201020209008812dc4bf7943e2b300a06082a8648ce3d0403023077310b3009060355040613024445310c300a06035504080c034e5257310f300d06035504070c06426f6368756d312f302d060355040a0c263c7363726970743e616c6572742827544c532d41747461636b657227293c2f7363726970743e3118301606035504030c0f746c732d61747461636b65722e6465301e170d3137303232323132353032385a170d3138303232323132353032385a3077310b3009060355040613024445310c300a06035504080c034e5257310f300d06035504070c06426f6368756d312f302d060355040a0c263c7363726970743e616c6572742827544c532d41747461636b657227293c2f7363726970743e3118301606035504030c0f746c732d61747461636b65722e64653059301306072a8648ce3d020106082a8648ce3d03010703420004fbca33b6018a6b244aea13a5332b505daa865026a565f7c7dc3aed6d8b8193248abb4000cf4a1c2c29d94ce1072454ea0a990cd97c863b931f266cc3addad922a350304e301d0603551d0e041604141e9b408ab6236764f8a1d26ed696f009d7b18904301f0603551d230418301680141e9b408ab6236764f8a1d26ed696f009d7b18904300c0603551d13040530030101ff300a06082a8648ce3d0403020349003046022100c9c06d798bbdf6809a3c9523bb979a64a0565fb1759182d6f6bcf6849cd70c7d022100b8e695c1915f71a348600ca90d48dfead7ea5c97b116b05c270af595c94bfa8d"));
        message.setCertificatesLength(573);
        handler.adjustTLSContext(message);
        assertNotNull(context.getClientCertificate());
        assertNotNull(context.getClientCertificatePublicKey());
        assertNull(context.getServerCertificate());
        context = new TlsContext();
        context.setTalkingConnectionEndType(ConnectionEndType.SERVER);
        handler = new CertificateHandler(context);
        handler.adjustTLSContext(message);
        assertNull(context.getClientCertificate());
        assertNotNull(context.getServerCertificate());
        assertNotNull(context.getServerCertificatePublicKey());

    }
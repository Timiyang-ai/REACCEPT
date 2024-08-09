@Test
    public void testDetermineIdentifiers() throws Exception {
        Dependency openssl = new Dependency();
        openssl.addEvidence(EvidenceType.VENDOR, "test", "vendor", "openssl", Confidence.HIGHEST);
        openssl.addEvidence(EvidenceType.PRODUCT, "test", "product", "openssl", Confidence.HIGHEST);
        openssl.addEvidence(EvidenceType.VERSION, "test", "version", "1.0.1c", Confidence.HIGHEST);

        CPEAnalyzer instance = new CPEAnalyzer();
        try (Engine engine = new Engine(getSettings())) {
            engine.openDatabase(true, true);
            instance.initialize(getSettings());
            instance.prepare(engine);
            instance.determineIdentifiers(openssl, "openssl", "openssl", Confidence.HIGHEST);
            instance.close();
        }

        String expResult = "cpe:/a:openssl:openssl:1.0.1c";
        Identifier expIdentifier = new Identifier("cpe", expResult, expResult);
        boolean found = false;
        for (Identifier i : openssl.getIdentifiers()) {
            if (expResult.equals(i.getValue())) {
                found = true;
                break;
            }
        }
        assertTrue("OpenSSL identifier not found", found);
    }
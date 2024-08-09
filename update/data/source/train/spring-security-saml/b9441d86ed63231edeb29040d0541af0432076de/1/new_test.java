@Test
    public void testGenerateExtendedMetadata() {

        ExtendedMetadata extendedMetadata;

        generator.setExtendedMetadata(new ExtendedMetadata());
        generator.setEntityBaseURL("http://localhost:8080");
        generator.getExtendedMetadata().setAlias("testAlias");
        generator.getExtendedMetadata().setIdpDiscoveryEnabled(true);

        // Default generation
        extendedMetadata = generator.generateExtendedMetadata();
        assertEquals("testAlias", extendedMetadata.getAlias());
        assertTrue(extendedMetadata.isLocal());
        assertTrue(extendedMetadata.isIdpDiscoveryEnabled());
        assertEquals("http://localhost:8080/saml/discovery/alias/testAlias", extendedMetadata.getIdpDiscoveryURL());
        assertEquals("http://localhost:8080/saml/login/alias/testAlias?disco=true", extendedMetadata.getIdpDiscoveryResponseURL());

        // Disabled discovery
        generator.getExtendedMetadata().setIdpDiscoveryEnabled(false);
        generator.setIncludeDiscoveryExtension(false);
        extendedMetadata = generator.generateExtendedMetadata();
        assertFalse(extendedMetadata.isIdpDiscoveryEnabled());
        assertNull(extendedMetadata.getIdpDiscoveryURL());
        assertNull(extendedMetadata.getIdpDiscoveryResponseURL());

        // Default extended metadata
        generator.setExtendedMetadata(new ExtendedMetadata());
        generator.getExtendedMetadata().setRequireLogoutResponseSigned(true);
        generator.getExtendedMetadata().setIdpDiscoveryEnabled(true);
        generator.getExtendedMetadata().setIdpDiscoveryResponseURL("http://testDisco.com/response");
        generator.setIncludeDiscoveryExtension(true);

        extendedMetadata = generator.generateExtendedMetadata();
        assertTrue(extendedMetadata.isIdpDiscoveryEnabled());
        assertEquals("http://localhost:8080/saml/discovery", extendedMetadata.getIdpDiscoveryURL());
        assertEquals("http://testDisco.com/response", extendedMetadata.getIdpDiscoveryResponseURL());
        assertTrue(extendedMetadata.isRequireLogoutResponseSigned());

    }
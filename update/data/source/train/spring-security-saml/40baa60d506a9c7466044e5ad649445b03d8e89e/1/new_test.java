@Test
    public void testGenerateExtendedMetadata() {

        ExtendedMetadata extendedMetadata;

        generator.setEntityAlias("testAlias");
        generator.setEntityBaseURL("http://localhost:8080");

        // Default generation
        extendedMetadata = generator.generateExtendedMetadata();
        assertEquals("testAlias", extendedMetadata.getAlias());
        assertTrue(extendedMetadata.isLocal());
        assertTrue(extendedMetadata.isIdpDiscoveryEnabled());
        assertEquals("http://localhost:8080/saml/discovery/alias/testAlias", extendedMetadata.getIdpDiscoveryURL());
        assertEquals("http://localhost:8080/saml/login/alias/testAlias?disco=true", extendedMetadata.getIdpDiscoveryResponseURL());

        // Disabled discovery
        generator.setIncludeDiscovery(false);
        generator.setIncludeDiscoveryExtension(false);
        extendedMetadata = generator.generateExtendedMetadata();
        assertFalse(extendedMetadata.isIdpDiscoveryEnabled());
        assertNull(extendedMetadata.getIdpDiscoveryURL());
        assertNull(extendedMetadata.getIdpDiscoveryResponseURL());

        // Default extended metadata
        ExtendedMetadata defaultMetadata = new ExtendedMetadata();
        defaultMetadata.setRequireLogoutResponseSigned(true);
        generator.setExtendedMetadata(defaultMetadata);
        generator.setIncludeDiscovery(true);
        generator.setIncludeDiscoveryExtension(true);
        generator.setCustomDiscoveryResponseURL("http://testDisco.com/response");
        extendedMetadata = generator.generateExtendedMetadata();
        assertTrue(extendedMetadata.isIdpDiscoveryEnabled());
        assertEquals("http://localhost:8080/saml/discovery/alias/testAlias", extendedMetadata.getIdpDiscoveryURL());
        assertEquals("http://testDisco.com/response", extendedMetadata.getIdpDiscoveryResponseURL());
        assertTrue(extendedMetadata.isRequireLogoutResponseSigned());

    }
@Test
    public void getChangedProperties_oneChange() {
        Map<String, Object> cfg = new HashMap<String, Object>();
        mockCookie(cfg, false);
        cfg.put("ssoCookieName", "webSSOCookie");
        cfg.put("ssoDomainNames", "austin.ibm.com|raleigh.ibm.com|useDomainFromURL");
        WebAppSecurityConfig webCfgOld = new WebAppSecurityConfigImpl(cfg, locationAdminRef, securityServiceRef, null, null);

        String newValue = "austin.ibm.com|raleigh.ibm.com";
        // Intentionally causing a new String to be created to guard against
        // accidentally doing instance comparison
        cfg.put("ssoCookieName", new String("webSSOCookie"));
        cfg.put("ssoDomainNames", newValue);
        WebAppSecurityConfig webCfg = new WebAppSecurityConfigImpl(cfg, locationAdminRef, securityServiceRef, null, null);
        assertEquals("When one setting has changed, that new value should be returned",
                     "ssoDomainNames=" + newValue, webCfg.getChangedProperties(webCfgOld));
    }
@Test
    public void testGetEvidence() {
        Dependency instance = new Dependency();
        Set<Evidence> result = instance.getEvidence(EvidenceType.VENDOR);
        assertNotNull(result);
        result = instance.getEvidence(EvidenceType.PRODUCT);
        assertNotNull(result);
        result = instance.getEvidence(EvidenceType.VERSION);
        assertNotNull(result);
    }
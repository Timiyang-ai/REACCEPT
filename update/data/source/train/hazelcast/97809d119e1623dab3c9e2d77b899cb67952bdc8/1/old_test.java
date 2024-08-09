@Test
    public void testCheckMergePolicySupportsInMemoryFormat_withLegacyMergePolicy_with39_NATIVE() {
        Object legacyMergePolicy = mapMergePolicyProvider.getMergePolicy(PutIfAbsentMapMergePolicy.class.getName());
        assertFalse(checkMergePolicySupportsInMemoryFormat("myMap", legacyMergePolicy, NATIVE, Versions.V3_9, false, LOGGER));
    }
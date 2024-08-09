@Test
    public void testCheckMergePolicySupportsInMemoryFormat_withLegacyMergePolicy_NATIVE() {
        Object legacyMergePolicy = mapMergePolicyProvider.getMergePolicy(PutIfAbsentMapMergePolicy.class.getName());
        assertFalse(checkMergePolicySupportsInMemoryFormat("myMap", legacyMergePolicy, NATIVE, false, LOGGER));
    }
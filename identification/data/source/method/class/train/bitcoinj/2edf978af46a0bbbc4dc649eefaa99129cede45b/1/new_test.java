    @Test
    public void addFollowingAccounts() throws Exception {
        assertFalse(group.isMarried());
        group.addAndActivateHDChain(createMarriedKeyChain());
        assertTrue(group.isMarried());
    }
    @Test
    public void isInSyncTestScanIncomplete() {
        when(milestoneTracker.isInitialScanComplete()).thenReturn(false);
        
        assertFalse("We should be out of sync when he havent finished initial scans", lsManager.isInSync(milestoneTracker));
    }
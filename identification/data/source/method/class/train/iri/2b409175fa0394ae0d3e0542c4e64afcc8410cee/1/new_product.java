@VisibleForTesting
    boolean isInSync(LatestMilestoneTracker latestMilestoneTracker) {
        if (!latestMilestoneTracker.isInitialScanComplete()) {
            return false;
        }

        int latestIndex = latestMilestoneTracker.getLatestMilestoneIndex();
        int latestSnapshot = snapshotProvider.getLatestSnapshot().getIndex();

        // If we are out of sync, only a full sync will get us in
        if (!isInSync && latestIndex == latestSnapshot) {
            isInSync = true;

        // When we are in sync, only dropping below the buffer gets us out of sync
        } else if (latestSnapshot < latestIndex - LOCAL_SNAPSHOT_SYNC_BUFFER) {
            isInSync = false;
        }

        return isInSync;
    }
@VisibleForTesting
    void checkDiskFull(File dir) throws DiskOutOfSpaceException, DiskWarnThresholdException {
        if (null == dir) {
            return;
        }
        if (dir.exists()) {
            long usableSpace = dir.getUsableSpace();
            long totalSpace = dir.getTotalSpace();
            float free = (float) usableSpace / (float) totalSpace;
            float used = 1f - free;
            if (used > diskUsageThreshold) {
                throw new DiskOutOfSpaceException("Space left on device "
                        + usableSpace + " Used space fraction:" + used + " < threshhold " + diskUsageThreshold);
            }
            // Warn should be triggered only if disk usage threshold doesn't trigger first.
            if (used > diskUsageWarnThreshold) {
                throw new DiskWarnThresholdException("Space left on device:"
                        + usableSpace + " Used space fraction:" + used +" < WarnThreshold:" + diskUsageWarnThreshold);
            }
        } else {
            checkDiskFull(dir.getParentFile());
        }
    }
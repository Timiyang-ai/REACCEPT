@VisibleForTesting
    void checkDiskFull(File dir) throws DiskOutOfSpaceException {
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
                        + usableSpace + " < threshhold " + diskUsageThreshold);
            }
        } else {
            checkDiskFull(dir.getParentFile());
        }
    }
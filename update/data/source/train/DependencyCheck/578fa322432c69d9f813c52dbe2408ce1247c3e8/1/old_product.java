protected boolean shouldUpdate(final long lastChecked, final long now, final DatabaseProperties properties,
            String currentVersion) throws UpdateException {
        //check every 30 days if we know there is an update, otherwise check every 7 days
        int checkRange = 30;
        if (updateToVersion == null || updateToVersion.isEmpty()) {
            checkRange = 7;
        }
        if (!DateUtil.withinDateRange(lastChecked, now, checkRange)) {
            LOGGER.debug("Checking web for new version.");
            final String currentRelease = getCurrentReleaseVersion();
            if (currentRelease != null) {
                final DependencyVersion v = new DependencyVersion(currentRelease);
                if (v.getVersionParts() != null && v.getVersionParts().size() >= 3) {
                    updateToVersion = v.toString();
                    if (!currentRelease.equals(updateToVersion)) {
                        properties.save(CURRENT_ENGINE_RELEASE, updateToVersion);
                    }
                    properties.save(ENGINE_VERSION_CHECKED_ON, Long.toString(now));
                }
            }
            LOGGER.debug("Current Release: {}", updateToVersion);
        }
        if (updateToVersion == null) {
            LOGGER.debug("Unable to obtain current release");
            return false;
        }
        final DependencyVersion running = new DependencyVersion(currentVersion);
        final DependencyVersion released = new DependencyVersion(updateToVersion);
        if (running.compareTo(released) < 0) {
            LOGGER.debug("Upgrade recommended");
            return true;
        }
        LOGGER.debug("Upgrade not needed");
        return false;
    }
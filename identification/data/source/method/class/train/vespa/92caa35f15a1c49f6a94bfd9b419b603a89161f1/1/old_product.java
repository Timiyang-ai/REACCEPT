private boolean completeUpgrade(List<ZoneId> zones, Version version) {
        boolean completed = true;
        for (ZoneId zone : zones) {
            startUpgrade(zone, version);
            completed = completed && !isUpgrading(zone);
        }
        return completed;
    }
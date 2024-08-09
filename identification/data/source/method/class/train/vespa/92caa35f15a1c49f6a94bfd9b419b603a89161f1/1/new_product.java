private boolean deploy(List<ZoneId> zones, SystemApplication application, Version version) {
        boolean completed = true;
        for (ZoneId zone : zones) {
            if (!wantedVersion(zone, application.id()).equals(version)) {
                log.info(String.format("Deploying %s version %s in %s", application.id(), version, zone));
                controller().applications().deploy(application, zone, version);
            }
            completed = completed && currentVersion(zone, application.id()).equals(version);
        }
        return completed;
    }
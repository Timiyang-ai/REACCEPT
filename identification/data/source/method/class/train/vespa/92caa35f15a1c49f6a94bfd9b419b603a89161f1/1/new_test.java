    private void completeUpgrade(SystemApplication application, Version version, ZoneApi... zones) {
        assertWantedVersion(application, version, zones);
        for (ZoneApi zone : zones) {
            for (Node node : listNodes(zone, application)) {
                nodeRepository().putByHostname(
                        zone.getId(),
                        new Node.Builder(node).currentVersion(node.wantedVersion()).build());
            }

            assertCurrentVersion(application, version, zone);
        }
    }
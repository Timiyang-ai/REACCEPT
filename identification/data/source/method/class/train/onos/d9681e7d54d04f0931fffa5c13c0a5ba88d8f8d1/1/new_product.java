public Optional<DeviceId> vRouterId() {
        if (!object.has(VROUTER_ID)) {
            return Optional.empty();
        }

        try {
            return Optional.of(DeviceId.deviceId(object.path(VROUTER_ID).asText()));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
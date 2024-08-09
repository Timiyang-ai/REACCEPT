public DeviceId vRouterId() {
        if (!object.has(VROUTER_ID)) {
            return null;
        }

        try {
            return DeviceId.deviceId(object.path(VROUTER_ID).asText());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
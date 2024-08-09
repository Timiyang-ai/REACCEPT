public List<Ip4Prefix> getSubnets(DeviceId deviceId) {
        if (deviceConfigMap.get(deviceId) != null) {
            log.debug("getSubnets for device{} is {}",
                    deviceId,
                    deviceConfigMap.get(deviceId).subnets.values());
            return new ArrayList<Ip4Prefix>(deviceConfigMap.
                    get(deviceId).subnets.values());
        } else {
            return null;
        }
    }
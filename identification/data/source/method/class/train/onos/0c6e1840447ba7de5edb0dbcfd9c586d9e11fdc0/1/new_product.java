@Override
    public synchronized MastershipTerm getTermFor(DeviceId deviceId) {
        if ((termMap.get(deviceId) == null)) {
            return MastershipTerm.of(masterMap.get(deviceId), NOTHING);
        }
        return MastershipTerm.of(
                masterMap.get(deviceId), termMap.get(deviceId).get());
    }
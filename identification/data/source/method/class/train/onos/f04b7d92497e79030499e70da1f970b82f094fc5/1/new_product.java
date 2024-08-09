private int modifyNextObjective(DeviceId deviceId, PortNumber portNumber,
                                    VlanId vlanId, boolean popVlan, boolean install) {
        int nextId = flowObjectiveService.allocateNextId();
        NextObjective.Builder nextObjBuilder = DefaultNextObjective
                .builder().withId(nextId)
                .withType(NextObjective.Type.SIMPLE)
                .fromApp(appId);

        TrafficTreatment.Builder ttBuilder = DefaultTrafficTreatment.builder();
        if (popVlan) {
            ttBuilder.popVlan();
        }
        ttBuilder.setOutput(portNumber);

        // setup metadata to pass to nextObjective - indicate the vlan on egress
        // if needed by the switch pipeline.
        TrafficSelector.Builder metabuilder = DefaultTrafficSelector.builder();
        metabuilder.matchVlanId(vlanId);

        nextObjBuilder.withMeta(metabuilder.build());
        nextObjBuilder.addTreatment(ttBuilder.build());
        log.debug("Submitted next objective {} in device {} for port/vlan {}/{}",
                nextId, deviceId, portNumber, vlanId);
        if (install) {
             flowObjectiveService.next(deviceId, nextObjBuilder.add());
        } else {
             flowObjectiveService.next(deviceId, nextObjBuilder.remove());
        }
        return nextId;
    }
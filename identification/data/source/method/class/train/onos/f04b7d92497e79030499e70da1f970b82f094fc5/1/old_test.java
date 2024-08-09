    private int modifyNextObjective(DeviceId deviceId, PortNumber portNumber, VlanId vlanId, boolean popVlan,
            boolean modifyFlag) {
        NextObjective.Builder nextObjBuilder = DefaultNextObjective.builder().withId(1)
                .withType(NextObjective.Type.SIMPLE).fromApp(APPID);

        TrafficTreatment.Builder ttBuilder = DefaultTrafficTreatment.builder();
        if (popVlan) {
            ttBuilder.popVlan();
        }
        ttBuilder.setOutput(portNumber);

        TrafficSelector.Builder metabuilder = DefaultTrafficSelector.builder();
        metabuilder.matchVlanId(vlanId);

        nextObjBuilder.withMeta(metabuilder.build());
        nextObjBuilder.addTreatment(ttBuilder.build());
        if (modifyFlag) {
            flowObjectiveService.next(deviceId, nextObjBuilder.add());
            expectLastCall().once();
        } else {
            flowObjectiveService.next(deviceId, nextObjBuilder.remove());
            expectLastCall().once();
        }
        return 1;
    }
private ForwardingObjective buildForwardingObjective(TrafficSelector selector,
                                                         TrafficTreatment treatment) {

        return DefaultForwardingObjective.builder()
                .withSelector(selector)
                .withTreatment(treatment)
                .fromApp(appId)
                .withPriority(PRIORITY)
                .withFlag(ForwardingObjective.Flag.VERSATILE)
                .add();
    }
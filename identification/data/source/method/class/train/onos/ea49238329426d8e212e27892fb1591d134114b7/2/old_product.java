private ForwardingObjective buildForwardingObjective(TrafficSelector selector,
                                                         TrafficTreatment treatment,
                                                         boolean add) {

        ForwardingObjective.Builder fobBuilder = DefaultForwardingObjective.builder()
                .withSelector(selector)
                .withTreatment(treatment)
                .fromApp(appId)
                .withPriority(PRIORITY)
                .withFlag(ForwardingObjective.Flag.VERSATILE);

        return add ? fobBuilder.add() : fobBuilder.remove();
    }
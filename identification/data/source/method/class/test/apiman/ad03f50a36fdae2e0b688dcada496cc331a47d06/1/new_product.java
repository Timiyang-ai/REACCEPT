@Override
    public void apply(ServiceRequest request, IPolicyContext context, Object config, IPolicyChain chain) {
        IPWhitelistConfig wc = (IPWhitelistConfig) config;
        if (wc.getIpList().contains(request.getRemoteAddr())) {
            chain.doApply(request);
        } else {
            IPolicyFailureFactoryComponent ffactory = context.getComponent(IPolicyFailureFactoryComponent.class);
            String msg = Messages.i18n.format("IPWhitelistPolicy.NotWhitelisted", request.getRemoteAddr()); //$NON-NLS-1$
            chain.doFailure(ffactory.createFailure(PolicyFailureType.Other, FailureCodes.IP_NOT_WHITELISTED, msg));
        }
    }
@Override
    public void apply(ServiceRequest request, IPolicyContext context, Object config, IPolicyChain chain) {
        IPWhitelistConfig wc = (IPWhitelistConfig) config;
        if (wc.getIpList().contains(request.getRemoteAddr())) {
            chain.doApply(request);
        } else {
            String msg = Messages.i18n.format("IPWhitelistPolicy.NotWhitelisted", request.getRemoteAddr()); //$NON-NLS-1$
            chain.doFailure(PolicyFailureFactory.createFailure(PolicyFailureType.Other, FailureCodes.IP_NOT_WHITELISTED, msg));
        }
    }
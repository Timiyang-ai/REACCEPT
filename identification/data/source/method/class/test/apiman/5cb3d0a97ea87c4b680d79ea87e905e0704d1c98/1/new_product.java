@Override
    public void apply(ServiceRequest request, IPolicyContext context, IPolicyChain<ServiceRequest> chain) {
        context.setAttribute("X-Conversation-Success", "true");
        chain.doApply(request);
    }
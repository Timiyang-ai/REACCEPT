@Override
    public void apply(ServiceRequest request, IPolicyContext context, Object config, IPolicyChain chain) {
        context.setAttribute("X-Conversation-Success", "true"); //$NON-NLS-1$ //$NON-NLS-2$
        chain.doApply(request);
    }
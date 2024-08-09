@Override
    public void update(Object args, Observable observable) {
        if(!(observable instanceof Tunnel)) {
            logger.error("[update] should observe tunnel only, not {}", observable.getClass().getName());
            return;
        }
        DefaultTunnel tunnel = (DefaultTunnel) observable;

        // deal with TunnelStateChangeEvent Only for current
        if(!(args instanceof TunnelStateChangeEvent)) {
            return;
        }
        TunnelStateChangeEvent event = (TunnelStateChangeEvent) args;

        if(event.getCurrent() instanceof TunnelClosed) {
            logger.info("[update] tunnel closed, remove from tunnel manager");
            remove(tunnel.frontendChannel());

        } else if(event.getCurrent() instanceof FrontendClosed) {
            logger.info("[update] Frontend closed, tunnel: {}", tunnel.getTunnelMeta());
            new TunnelFrontendClosedEventHandler(tunnel, event).handle();

        } else if(event.getCurrent() instanceof BackendClosed) {
            logger.info("[update] Backend closed, tunnel: {}", tunnel.getTunnelMeta());
            new TunnelBackendClosedEventHandler(tunnel, event).handle();

        } else if(event.getCurrent() instanceof TunnelClosing) {
            try {
                // ensure the concurrent situation
                if(tunnel.getState() instanceof TunnelClosing) {
                    tunnel.release();
                    remove(tunnel.frontendChannel());
                }
            } catch (Exception e) {
                logger.error("[update] try to stop tunnel failed: ", e);
            }
        }
    }
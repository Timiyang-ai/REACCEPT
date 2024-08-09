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
            tunnel.backend().release();
            tunnel.setState(new TunnelClosing(tunnel));

        } else if(event.getCurrent() instanceof BackendClosed) {
            logger.info("[update] Backend closed, tunnel: {}", tunnel.getTunnelMeta());
            tunnel.frontend().release();
            tunnel.setState(new TunnelClosing(tunnel));

        } else if(event.getCurrent() instanceof TunnelClosing) {
            try {
                LifecycleHelper.stopIfPossible(tunnel);
                remove(tunnel.frontendChannel());
            } catch (Exception e) {
                logger.error("[update] try to stop tunnel failed: ", e);
            }
        }
    }
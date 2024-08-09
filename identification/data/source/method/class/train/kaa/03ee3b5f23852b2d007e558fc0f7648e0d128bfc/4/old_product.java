public void registerListeners() {
        LOG.trace("DynamicLoadManager register listeners...");
        ControlNode pm = getLoadDistributionService().getZkService().getControlZKNode();
        pm.addListener((OperationsNodeListener) this);
        pm.addListener((BootstrapNodeListener) this);
    }
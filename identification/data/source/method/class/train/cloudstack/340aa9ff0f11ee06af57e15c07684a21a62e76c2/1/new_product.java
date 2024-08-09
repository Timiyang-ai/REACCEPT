@Override
    public boolean configure(String name, Map<String, Object> params)
            throws ConfigurationException {
        LOGGER.debug("configure " + name + " with params: " + params);
        /* check if we're master or not and if we can connect */
        try {
            configuration = new Ovm3Configuration(params);
            if (!configuration.getIsTest()) {
                c = new Connection(configuration.getAgentIp(),
                        configuration.getAgentOvsAgentPort(),
                        configuration.getAgentOvsAgentUser(),
                        configuration.getAgentOvsAgentPassword());
                c.setHostName(configuration.getAgentHostname());
            }
            hypervisorsupport = new Ovm3HypervisorSupport(c, configuration);
            if (!configuration.getIsTest()) {
                hypervisorsupport.setupServer(configuration
                        .getAgentSshKeyFileName());
            }
            hypervisorsupport.masterCheck();
        } catch (Exception e) {
            throw new CloudRuntimeException("Base checks failed for "
                    + configuration.getAgentHostname(), e);
        }
        hypervisornetwork = new Ovm3HypervisorNetwork(c, configuration);
        hypervisornetwork.configureNetworking();
        virtualroutingresource = new Ovm3VirtualRoutingResource(c);
        storagepool = new Ovm3StoragePool(c, configuration);
        storagepool.prepareForPool();
        storageprocessor = new Ovm3StorageProcessor(c, configuration,
                storagepool);
        vmsupport = new Ovm3VmSupport(c, configuration, hypervisorsupport,
                storageprocessor, storagepool, hypervisornetwork);
        vrResource = new VirtualRoutingResource(virtualroutingresource);
        if (!vrResource.configure(name, params)) {
            throw new ConfigurationException(
                    "Unable to configure VirtualRoutingResource");
        }
        guesttypes = new Ovm3VmGuestTypes();
        storageHandler = new StorageSubsystemCommandHandlerBase(storageprocessor);
        virtualroutingsupport = new Ovm3VirtualRoutingSupport(c, configuration,
                virtualroutingresource);
        setConfigParams(params);
        return true;
    }
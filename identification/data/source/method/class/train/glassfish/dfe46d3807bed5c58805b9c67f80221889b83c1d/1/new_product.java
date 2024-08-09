@Override
    protected void validate()
            throws CommandException {

        if(ok(instanceName0))
            instanceName = instanceName0;
        else
            throw new CommandException(Strings.get("Instance.badInstanceName"));

        isCreateInstanceFilesystem = true;

        super.validate();

        String agentPath = "agent" + File.separator + "config";
        agentConfigDir = new File(nodeDirChild, agentPath);
        dasPropsFile = new File(agentConfigDir, "das.properties");

        if (dasPropsFile.isFile()) {
            validateDasOptions(programOpts.getHost(), String.valueOf(programOpts.getPort()),
                    String.valueOf(programOpts.isSecure()), dasPropsFile);
            setDasDefaults(dasPropsFile);
            if (!setDasDefaultsOnly) {
                String nodeDirChildName = nodeDirChild != null ? nodeDirChild.getName() : "";
                String nodeName = node != null ? node : nodeDirChildName;
                logger.info(Strings.get("Instance.existingDasPropertiesWarning",
                    programOpts.getHost(), "" + programOpts.getPort(), nodeName));
            }
        }

        DASHost = programOpts.getHost();
        DASPort = programOpts.getPort();
        dasIsSecure = programOpts.isSecure();
        DASProtocol = "http";

    }
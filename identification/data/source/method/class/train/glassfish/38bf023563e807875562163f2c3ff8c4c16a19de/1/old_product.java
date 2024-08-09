public DFProgressObject deploy(Target[] targets, URI source, URI deploymentPlan, Map deploymentOptions) {
        ensureConnected();
        targets = prepareTargets(targets);
        String targetsParam = createTargetsParam(targets);
        deploymentOptions.put("target", targetsParam);
        ProgressObjectImpl po = new ProgressObjectImpl(targets);
        //Make sure the file permission is correct when deploying a file
        if (source == null) {
            po.setupForAbnormalExit(localStrings.getLocalString("enterprise.deployment.client.archive_not_specified", "Archive to be deployed is not specified at all."), domain);
            return po;
        }
        File tmpFile = new File(source.getSchemeSpecificPart());
        if (!tmpFile.exists()) {
            po.setupForAbnormalExit(localStrings.getLocalString("enterprise.deployment.client.archive_not_in_location", "Unable to find the archive to be deployed in specified location."), domain);
            return po;
        }
        if (!tmpFile.canRead()) {
            po.setupForAbnormalExit(localStrings.getLocalString("enterprise.deployment.client.archive_no_read_permission", "Archive to be deployed does not have read permission."), domain);
            return po;
        }
        boolean isDirectoryDeploy = tmpFile.isDirectory();
        try {
            if (deploymentPlan != null) {
                File dp = new File(deploymentPlan.getSchemeSpecificPart());
                if (!dp.exists()) {
                    po.setupForAbnormalExit(localStrings.getLocalString(
                            "enterprise.deployment.client.plan_not_in_location",
                            "Unable to find the deployment plan in specified location."), domain);
                    return po;
                }
                if (!dp.canRead()) {
                    po.setupForAbnormalExit(localStrings.getLocalString(
                            "enterprise.deployment.client.plan_no_read_permission",
                            "Deployment plan does not have read permission."), domain);
                    return po;
                }
                deploymentOptions.put(DFDeploymentProperties.DEPLOYMENT_PLAN, dp.getAbsolutePath());
            }

            // it's redeploy, set the enable attribute accordingly
            if (Boolean.valueOf((String)deploymentOptions.remove(
                DFDeploymentProperties.REDEPLOY))) {
                String appName = (String)deploymentOptions.get(
                    DFDeploymentProperties.NAME);
                String enabledAttr = getAppRefEnabledAttr(
                    targets[0].getName(), appName);
                deploymentOptions.put(DFDeploymentProperties.ENABLED, 
                    enabledAttr);
            }

            DFCommandRunner commandRunner = getDFCommandRunner(
                    "deploy", deploymentOptions, new String[]{tmpFile.getAbsolutePath()});
            DFDeploymentStatus ds = commandRunner.run();
            DFDeploymentStatus mainStatus = ds.getMainStatus();
            String moduleID = mainStatus.getProperty(DFDeploymentProperties.NAME);
            po.setModuleID(moduleID);

            if (mainStatus.getStatus() != DFDeploymentStatus.Status.FAILURE) {
                // TODO: support multiple targets
                TargetModuleIDImpl[] targetModuleIDs = new TargetModuleIDImpl[targets.length];
                int i = 0;
                for (TargetImpl ti : po.toTargetImpl(targets)) {
                    targetModuleIDs[i++] = new TargetModuleIDImpl(ti, moduleID);
                }

                po.setupForNormalExit(localStrings.getLocalString("enterprise.deployment.client.deploy_application", "Deployment of application {0}", moduleID), domain, mainStatus, targetModuleIDs);
            } else {
                po.setupForAbnormalExit(localStrings.getLocalString("enterprise.deployment.client.deploy_application_failed", "Deployment of application failed - {0}", mainStatus.getStageStatusMessage()), domain, mainStatus);
            }
            return po;
        } catch (Throwable ioex) {
            po.setupForAbnormalExit(localStrings.getLocalString("enterprise.deployment.client.deploy_application_failed", "Deployment of application failed - {0} ", ioex.toString()), domain, ioex);
            return po;
        }
    }
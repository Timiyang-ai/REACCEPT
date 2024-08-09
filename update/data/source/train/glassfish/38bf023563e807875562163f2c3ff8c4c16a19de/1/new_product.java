public DFProgressObject deploy(Target[] targets, URI source, URI deploymentPlan, Map deploymentOptions) {
        ensureConnected();
        targets = prepareTargets(targets);
        ProgressObjectImpl po = new ProgressObjectImpl(targets);
        List<TargetModuleIDImpl> targetModuleIDList =
            new ArrayList<TargetModuleIDImpl>();

        //Make sure the file permission is correct when deploying a file
        if (source == null) {
            po.setupForAbnormalExit(localStrings.getLocalString("enterprise.deployment.client.archive_not_specified", "Archive to be deployed is not specified at all."), (TargetImpl)targets[0]);
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
            boolean isRedeploy = Boolean.valueOf((String)deploymentOptions.remove(DFDeploymentProperties.REDEPLOY));
            if (isRedeploy) {
                String appName = (String)deploymentOptions.get(
                    DFDeploymentProperties.NAME);
                if (!isTargetsMatched(appName, targets)) {
                    po.setupForAbnormalExit(localStrings.getLocalString("enterprise.deployment.client.specifyAllTargets", "Application {0} is already deployed on other targets. Please remove all references or specify all targets (or domain target if using asadmin command line) before attempting {1} operation", appName, "redeploy"), domain);
                }
                // set the enable attribute accordingly
                String enabledAttr = getAppRefEnabledAttr(
                    targets[0].getName(), appName);
                deploymentOptions.put(DFDeploymentProperties.ENABLED, 
                    enabledAttr);
            }

            Target[] origTargets = targets;

            // first deploy to first target
            if (isRedeploy && targets.length > 1) {
                // if it's redeploy and having more than one target, 
                // we should just redeploy with the special domain target
                targets = createTargets(new String[] {"domain"});
            }
            deploymentOptions.put(DFDeploymentProperties.TARGET, targets[0].getName());    
            DFCommandRunner commandRunner = getDFCommandRunner(
                "deploy", deploymentOptions, new String[]{tmpFile.getAbsolutePath()});
            DFDeploymentStatus ds = commandRunner.run();
            DFDeploymentStatus mainStatus = ds.getMainStatus();
            String moduleID;
            if (!po.checkStatusAndAddStage((TargetImpl)targets[0], localStrings.getLocalString("enterprise.deployment.client.deploy_to_first_target", "Deploying application to target {0}", targets[0].getName()),  mainStatus)) {
                return po;
            } else {
                moduleID = mainStatus.getProperty(DFDeploymentProperties.NAME);
                po.setModuleID(moduleID);
            }

            Map createAppRefOptions = new HashMap();
            if (deploymentOptions.get(DFDeploymentProperties.ENABLED) != null) {
                createAppRefOptions.put(DFDeploymentProperties.ENABLED, deploymentOptions.get(DFDeploymentProperties.ENABLED)); 
            }
            if (deploymentOptions.get(DFDeploymentProperties.VIRTUAL_SERVERS) != null) {
                createAppRefOptions.put(DFDeploymentProperties.VIRTUAL_SERVERS, deploymentOptions.get(DFDeploymentProperties.VIRTUAL_SERVERS)); 
            }
            // then create application references to the rest of the targets
            for (int i = 1; i < targets.length; i++) {
                createAppRefOptions.put(DFDeploymentProperties.TARGET, targets[i].getName());    
                DFCommandRunner commandRunner2 = getDFCommandRunner(
                    "create-application-ref", createAppRefOptions, new String[] {moduleID});
                DFDeploymentStatus ds2 = commandRunner2.run();
                DFDeploymentStatus mainStatus2 = ds2.getMainStatus();
                if (!po.checkStatusAndAddStage((TargetImpl)targets[i], 
"create app ref", mainStatus2)) {
                    return po;
                } 
            }
 
            // we use origTargets to populate the targetModuleIDList 
            // so it takes care of the redeploy using domain target case too
            for (int i = 0; i < origTargets.length; i++) {
                TargetModuleIDImpl targetModuleID = new TargetModuleIDImpl((TargetImpl)origTargets[i], moduleID);
                targetModuleIDList.add(targetModuleID);
            }

            TargetModuleIDImpl[] targetModuleIDs =
                new TargetModuleIDImpl[targetModuleIDList.size()];
            targetModuleIDs = (TargetModuleIDImpl[])targetModuleIDList.toArray(targetModuleIDs);
            po.setupForNormalExit(localStrings.getLocalString("enterprise.deployment.client.deploy_application", "Deployment of application {0}", moduleID), (TargetImpl)targets[0], targetModuleIDs);
            return po;
        } catch (Throwable ioex) {
            po.setupForAbnormalExit(localStrings.getLocalString("enterprise.deployment.client.deploy_application_failed", "Deployment of application failed - {0} ", ioex.toString()), (TargetImpl)targets[0]);
            return po;
        }
    }
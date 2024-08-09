public static Action createAction(ActionType typeIn, Date earliest) {
        Action retval;
        if (typeIn.equals(TYPE_ERRATA)) {
            retval = new ErrataAction();
        }
        else if (typeIn.equals(TYPE_SCRIPT_RUN)) {
            retval = new ScriptRunAction();
        }
        else if (typeIn.equals(TYPE_CONFIGFILES_DIFF) ||
                typeIn.equals(TYPE_CONFIGFILES_DEPLOY) ||
                typeIn.equals(TYPE_CONFIGFILES_VERIFY)) {
            retval = new ConfigAction();
        }
        else if (typeIn.equals(TYPE_CONFIGFILES_UPLOAD)) {
            retval = new ConfigUploadAction();
        }
        else if (typeIn.equals(TYPE_PACKAGES_AUTOUPDATE) ||
                 typeIn.equals(TYPE_PACKAGES_DELTA) ||
                 typeIn.equals(TYPE_PACKAGES_REFRESH_LIST) ||
                 typeIn.equals(TYPE_PACKAGES_REMOVE) ||
                 typeIn.equals(TYPE_PACKAGES_RUNTRANSACTION) ||
                 typeIn.equals(TYPE_PACKAGES_UPDATE) ||
                 typeIn.equals(TYPE_PACKAGES_VERIFY) ||
                 typeIn.equals(TYPE_SOLARISPKGS_REMOVE) ||
                 typeIn.equals(TYPE_SOLARISPKGS_INSTALL)) {
           retval = new PackageAction();
        }
        else if (typeIn.equals(TYPE_CONFIGFILES_MTIME_UPLOAD)) {
            retval = new ConfigUploadMtimeAction();
        }
        //Kickstart Actions
        else if (typeIn.equals(TYPE_KICKSTART_SCHEDULE_SYNC)) {
            retval = new KickstartScheduleSyncAction();
        }
        else if (typeIn.equals(TYPE_KICKSTART_INITIATE)) {
            retval = new KickstartInitiateAction();
        }
        else if (typeIn.equals(TYPE_KICKSTART_INITIATE_GUEST)) {
            retval = new KickstartInitiateGuestAction();
        }
        else if (typeIn.equals(TYPE_DAEMON_CONFIG)) {
            retval = new DaemonConfigAction();
        }
        else if (typeIn.equals(TYPE_SOLARISPKGS_PATCHREMOVE)) {
            retval = new SolarisPackagePatchRemoveAction();
        }
        else if (typeIn.equals(TYPE_SOLARISPKGS_PATCHINSTALL)) {
            retval = new SolarisPackagePatchInstallAction();
        }
        else if (typeIn.equals(TYPE_SOLARISPKGS_PATCHCLUSTERINSTALL)) {
            retval = new SolarisPackagePatchClusterInstallAction();
        }
        else if (typeIn.equals(TYPE_VIRTUALIZATION_SHUTDOWN)) {
            retval = new VirtualizationShutdownAction();
        }
        else if (typeIn.equals(TYPE_VIRTUALIZATION_START)) {
            retval = new VirtualizationStartAction();
        }
        else if (typeIn.equals(TYPE_VIRTUALIZATION_SUSPEND)) {
            retval = new VirtualizationSuspendAction();
        }
        else if (typeIn.equals(TYPE_VIRTUALIZATION_RESUME)) {
            retval = new VirtualizationResumeAction();
        }
        else if (typeIn.equals(TYPE_VIRTUALIZATION_REBOOT)) {
            retval = new VirtualizationRebootAction();
        }
        else if (typeIn.equals(TYPE_VIRTUALIZATION_DESTROY)) {
            retval = new VirtualizationDestroyAction();
        }
        else if (typeIn.equals(TYPE_VIRTUALIZATION_SET_MEMORY)) {
            retval = new VirtualizationSetMemoryAction();
        }
        else if (typeIn.equals(TYPE_VIRTUALIZATION_SET_VCPUS)) {
            retval = new VirtualizationSetVcpusAction();
        }
        else if (typeIn.equals(TYPE_VIRTUALIZATION_SCHEDULE_POLLER)) {
            retval = new VirtualizationSchedulePollerAction();
        }
        else if (typeIn.equals(TYPE_VIRTIZATION_HOST_SUBSCRIBE_TO_TOOLS_CHANNEL)) {
            retval = new KickstartHostToolsChannelSubscriptionAction();
        }
        else if (typeIn.equals(TYPE_VIRTUALIZATION_GUEST_SUBSCRIBE_TO_TOOLS_CHANNEL)) {
            retval = new KickstartGuestToolsChannelSubscriptionAction();
        }
        else if (typeIn.equals(TYPE_SCAP_XCCDF_EVAL)) {
            retval = new ScapAction();
        }

        else {
            retval = new Action();
        }
        retval.setActionType(typeIn);
        retval.setCreated(new Date());
        retval.setModified(new Date());
        if (earliest == null) {
            earliest = new Date();
        }
        retval.setEarliestAction(earliest);
        //in perl(modules/rhn/RHN/DB/Scheduler.pm) version is given a 2.
        //So that's what I did.
        retval.setVersion(new Long(2));
        retval.setArchived(new Long(0)); //not archived
        return retval;
    }
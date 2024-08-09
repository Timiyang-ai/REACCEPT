protected void findLocations(String newServerName,
                                 String instanceDirStr,
                                 String outputDirStr,
                                 String logDirStr,
                                 String consoleLogFileStr) throws LocationException {

        // Server name only found via command line
        setProcessName(newServerName);

        // always use the parent of the lib dir as WLP_INSTALL_ROOT
        installRoot = bootstrapLib.getParentFile();

        // WLP_USER_DIR = /wlp/usr
        if (instanceDirStr == null)
            userRoot = new File(installRoot, BootstrapConstants.LOC_AREA_NAME_USR);
        else
            userRoot = assertDirectory(FileUtils.normalize(instanceDirStr), BootstrapConstants.ENV_WLP_USER_DIR);

        // /wlp/usr/servers
        processesRoot = new File(userRoot, getProcessesSubdirectory());
        // /wlp/usr/servers/serverName
        configDir = new File(processesRoot, processName);

        // Canonicalize server name for case-insensitive file systems.
        // UNLESS it is a symlink, in which case we just try to match case.
        String canonicalServerName = processName;
        try {
            // canonicalServerName = configDir.getCanonicalFile().getName();
            File parentDir = configDir.getParentFile();
            if (!isSymbolicLink(configDir, parentDir)) {
                canonicalServerName = configDir.getCanonicalFile().getName();
                if (!processName.equals(canonicalServerName)) {
                    processName = canonicalServerName;
                    // Recreate configDir (rather than using the result of
                    // getCanonicalFile above) to preserve symlinks.
                    configDir = new File(processesRoot, processName);
                }
            } else {
                // Find exact match, OR find case-variant if exact fails.
                File candidate = null;
                File[] siblings = parentDir.listFiles();
                File canonicalConfigDir = configDir.getCanonicalFile();
                for (int i = 0; i < siblings.length; ++i) {
                    File sibling = siblings[i];
                    if (!sibling.isDirectory())
                        continue;
                    String sibname = sibling.getCanonicalFile().getName();
                    if (sibname.equals(processName)) {
                        candidate = sibling;
                        break; // exact match exists, use as it stands
                    } else if (sibname.equalsIgnoreCase(processName)) {
                        if (sibling.getCanonicalFile().equals(canonicalConfigDir))
                            candidate = sibling; // Not exact match, but same file.
                        // Continue scanning in case exact match also exists.
                        // If several exist with varying case but nothing is exact...
                        // we currently take last-found; could  instead order lexically, or
                        // could tell the user to stop typing nonsense.
                    }
                }
                if (candidate != null) {
                    processName = candidate.getName();
                    // Recreate configDir (rather than using the result of
                    // getCanonicalFile above) to preserve symlinks.
                    configDir = new File(processesRoot, processName);
                }
            }
        } catch (IOException e) {
            // Ignore.
        }

        if (outputDirStr == null) {
            outputRoot = processesRoot;
            outputDir = configDir;
        } else {
            // separate output dir, WLP_OUTPUT_DIR
            outputRoot = assertDirectory(FileUtils.normalize(outputDirStr), getOutputDirectoryEnvName());
            outputDir = new File(outputRoot, processName);
        }

        // Logs could be redirected to a place other than the server output dir (like /var/log.. )
        if (logDirStr == null)
            logDir = new File(outputDir, BootstrapConstants.LOC_AREA_NAME_LOGS);
        else
            logDir = assertDirectory(FileUtils.normalize(logDirStr), BootstrapConstants.ENV_LOG_DIR);
        consoleLogFile = new File(logDir, consoleLogFileStr != null ? consoleLogFileStr : BootstrapConstants.CONSOLE_LOG);

        // Server workarea always a child of outputDir
        workarea = new File(outputDir, BootstrapConstants.LOC_AREA_NAME_WORKING);
    }
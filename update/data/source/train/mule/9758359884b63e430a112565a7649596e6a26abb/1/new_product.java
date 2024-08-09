public static File getMuleAppsDir()
    {
        return isStandalone() ? new File(getMuleHome(), MULE_APPS_FILENAME) : null;
    }
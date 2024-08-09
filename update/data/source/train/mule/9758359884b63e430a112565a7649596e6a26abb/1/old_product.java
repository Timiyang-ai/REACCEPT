public static File getMuleAppsFile()
    {
        return isStandalone() ? new File(getMuleHomeFile(), MULE_APPS_FILENAME) : null;
    }
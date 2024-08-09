public static File getMuleHome()
    {
        return isStandalone() ? new File(MULE_HOME) : null;
    }
public static File getMuleHomeFile()
    {
        return isStandalone() ? new File(MULE_HOME) : null;
    }
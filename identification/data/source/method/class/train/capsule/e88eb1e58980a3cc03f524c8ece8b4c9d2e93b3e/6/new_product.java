protected String expand(String str) {
        if ("$0".equals(str))
            return processOutgoingPath(jarFile);

        str = expandCommandLinePath(str);

        if (appCache != null)
            str = str.replace("$" + VAR_CAPSULE_DIR, processOutgoingPath(appCache));
        else if (str.contains("$" + VAR_CAPSULE_DIR))
            throw new IllegalStateException("The $" + VAR_CAPSULE_DIR + " variable cannot be expanded when the capsule is not extracted");

        if (appId != null)
            str = str.replace("$" + VAR_CAPSULE_APP, appId);
        else if (str.contains("$" + VAR_CAPSULE_APP))
            throw new IllegalStateException("Cannot use $" + VAR_CAPSULE_APP + " variable in an empty capsule. (in: " + str + ")");
        
        str = str.replace("$" + VAR_CAPSULE_JAR, processOutgoingPath(jarFile));
        final String jhome = processOutgoingPath(getJavaHome());
        
        if (jhome != null)
            str = str.replace("$" + VAR_JAVA_HOME, jhome);
        str = str.replace('/', FILE_SEPARATOR_CHAR);
        return str;
    }
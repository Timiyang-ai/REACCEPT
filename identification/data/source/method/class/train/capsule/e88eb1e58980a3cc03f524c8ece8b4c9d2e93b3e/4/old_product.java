protected String expand(String str) {
        if (appCache != null)
            str = str.replaceAll("\\$" + VAR_CAPSULE_DIR, appCache.toAbsolutePath().toString());
        else if (str.contains("$" + VAR_CAPSULE_DIR))
            throw new IllegalStateException("The $" + VAR_CAPSULE_DIR + " variable cannot be expanded when the "
                    + ATTR_EXTRACT + " attribute is set to false");

        str = expandCommandLinePath(str);
        assert appId != null;
        str = str.replaceAll("\\$" + VAR_CAPSULE_APP, appId);
        str = str.replaceAll("\\$" + VAR_CAPSULE_JAR, jarFile.toString());
        str = str.replaceAll("\\$" + VAR_JAVA_HOME, javaHome != null ? javaHome : System.getProperty(PROP_JAVA_HOME));
        str = str.replace('/', FILE_SEPARATOR.charAt(0));
        return str;
    }
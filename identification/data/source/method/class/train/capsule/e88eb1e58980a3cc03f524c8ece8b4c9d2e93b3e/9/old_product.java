protected String expand(String str) {
        if ("$0".equals(str))
            return jarFile.toString();

        if (appCache != null)
            str = str.replaceAll("\\$" + VAR_CAPSULE_DIR, appCache.toAbsolutePath().toString());
        else if (str.contains("$" + VAR_CAPSULE_DIR))
            throw new IllegalStateException("The $" + VAR_CAPSULE_DIR + " variable cannot be expanded when the "
                    + ATTR_EXTRACT + " attribute is set to false");

        str = expandCommandLinePath(str);
        assert appId != null;
        str = str.replaceAll("\\$" + VAR_CAPSULE_APP, appId);
        str = str.replaceAll("\\$" + VAR_CAPSULE_JAR, jarFile.toString());
        final String jhome = toString(getJavaHome());
        if (jhome != null)
            str = str.replaceAll("\\$" + VAR_JAVA_HOME, jhome);
        str = str.replace('/', FILE_SEPARATOR_CHAR);
        return str;
    }
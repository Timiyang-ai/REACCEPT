static String isJavaDir(String fileName) {
        fileName = fileName.toLowerCase();
        if (fileName.startsWith("jdk") || fileName.startsWith("jre") || fileName.endsWith(".jdk") || fileName.endsWith(".jre")) {
            if (fileName.startsWith("jdk") || fileName.startsWith("jre"))
                fileName = fileName.substring(3);
            if (fileName.endsWith(".jdk") || fileName.endsWith(".jre"))
                fileName = fileName.substring(0, fileName.length() - 4);
            return shortJavaVersion(fileName);
        } else if (fileName.startsWith("java-") && (fileName.contains("-openjdk") || fileName.contains("-oracle"))) {
            final Matcher m = Pattern.compile("java-([0-9]+)-").matcher(fileName);
            m.find();
            return shortJavaVersion(m.group(1));
        } else
            return null;
    }
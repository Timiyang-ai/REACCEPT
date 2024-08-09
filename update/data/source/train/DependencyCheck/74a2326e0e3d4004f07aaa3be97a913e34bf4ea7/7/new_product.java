protected File getDataFile(String key) {
        final String file = getString(key);
        LOGGER.debug("Settings.getDataFile() - file: '{}'", file);
        if (file == null) {
            return null;
        }
        if (file.startsWith("[JAR]")) {
            LOGGER.debug("Settings.getDataFile() - transforming filename");
            final File jarPath = getJarPath();
            LOGGER.debug("Settings.getDataFile() - jar file: '{}'", jarPath.toString());
            final File retVal = new File(jarPath, file.substring(6));
            LOGGER.debug("Settings.getDataFile() - returning: '{}'", retVal.toString());
            return retVal;
        }
        return new File(file);
    }
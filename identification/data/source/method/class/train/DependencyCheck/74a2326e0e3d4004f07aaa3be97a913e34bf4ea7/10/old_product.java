public static String getConnectionString(String connectionStringKey, String dbFileNameKey)
            throws IOException, InvalidSettingException {
        final String connStr = Settings.getString(connectionStringKey);
        if (connStr == null) {
            final String msg = String.format("Invalid properties file; %s is missing.", connectionStringKey);
            throw new InvalidSettingException(msg);
        }
        if (connStr.contains("%s")) {
            final File directory = getDataDirectory();
            String fileName = null;
            if (dbFileNameKey != null) {
                fileName = Settings.getString(dbFileNameKey);
            }
            if (fileName == null) {
                final String msg = String.format("Invalid properties file to get a file based connection string; '%s' must be defined.",
                        dbFileNameKey);
                throw new InvalidSettingException(msg);
            }
            if (connStr.startsWith("jdbc:h2:file:") && fileName.endsWith(".h2.db")) {
                fileName = fileName.substring(0, fileName.length() - 6);
            }
            // yes, for H2 this path won't actually exists - but this is sufficient to get the value needed
            final File dbFile = new File(directory, fileName);
            final String cString = String.format(connStr, dbFile.getCanonicalPath());
            LOGGER.debug("Connection String: '{}'", cString);
            return cString;
        }
        return connStr;
    }
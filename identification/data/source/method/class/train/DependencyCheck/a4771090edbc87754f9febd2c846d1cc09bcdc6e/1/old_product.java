protected final Updateable updatesNeeded() throws MalformedURLException, DownloadFailedException, UpdateException {
        Updateable updates = null;
        try {
            updates = retrieveCurrentTimestampsFromWeb();
        } catch (InvalidDataException ex) {
            final String msg = "Unable to retrieve valid timestamp from nvd cve downloads page";
            Logger.getLogger(StandardUpdate.class.getName()).log(Level.FINE, msg, ex);
            throw new DownloadFailedException(msg, ex);
        } catch (InvalidSettingException ex) {
            Logger.getLogger(StandardUpdate.class.getName()).log(Level.FINE, "Invalid setting found when retrieving timestamps", ex);
            throw new DownloadFailedException("Invalid settings", ex);
        }

        if (updates == null) {
            throw new DownloadFailedException("Unable to retrieve the timestamps of the currently published NVD CVE data");
        }
        if (!properties.isEmpty()) {
            try {
                float version;

                if (properties.getProperty("version") == null) {
                    deleteAndRecreate = true;
                } else {
                    try {
                        version = Float.parseFloat(properties.getProperty("version"));
                        final float currentVersion = Float.parseFloat(CveDB.DB_SCHEMA_VERSION);
                        if (currentVersion > version) {
                            deleteAndRecreate = true;
                        }
                    } catch (NumberFormatException ex) {
                        deleteAndRecreate = true;
                    }
                }

                if (deleteAndRecreate) {
                    return updates;
                }

                final long lastUpdated = Long.parseLong(properties.getProperty(DatabaseProperties.LAST_UPDATED, "0"));
                final Date now = new Date();
                final int days = Settings.getInt(Settings.KEYS.CVE_MODIFIED_VALID_FOR_DAYS, 7);
                if (lastUpdated == updates.getTimeStamp(MODIFIED)) {
                    updates.clear(); //we don't need to update anything.
                } else if (withinRange(lastUpdated, now.getTime(), days)) {
                    for (NvdCveInfo entry : updates) {
                        if (MODIFIED.equals(entry.getId())) {
                            entry.setNeedsUpdate(true);
                        } else {
                            entry.setNeedsUpdate(false);
                        }
                    }
                } else { //we figure out which of the several XML files need to be downloaded.
                    for (NvdCveInfo entry : updates) {
                        if (MODIFIED.equals(entry.getId())) {
                            entry.setNeedsUpdate(true);
                        } else {
                            long currentTimestamp = 0;
                            try {
                                currentTimestamp = Long.parseLong(properties.getProperty(DatabaseProperties.LAST_UPDATED_BASE + entry.getId(), "0"));
                            } catch (NumberFormatException ex) {
                                final String msg = String.format("Error parsing '%s' '%s' from nvdcve.lastupdated",
                                        DatabaseProperties.LAST_UPDATED_BASE, entry.getId());
                                Logger
                                        .getLogger(StandardUpdate.class
                                        .getName()).log(Level.FINE, msg, ex);
                            }
                            if (currentTimestamp == entry.getTimestamp()) {
                                entry.setNeedsUpdate(false);
                            }
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                final String msg = "An invalid schema version or timestamp exists in the data.properties file.";
                Logger
                        .getLogger(StandardUpdate.class
                        .getName()).log(Level.WARNING, msg);
                Logger.getLogger(StandardUpdate.class
                        .getName()).log(Level.FINE, null, ex);
            }
        }
        return updates;
    }
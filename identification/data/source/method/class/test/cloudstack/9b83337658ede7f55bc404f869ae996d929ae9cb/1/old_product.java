public static String buildConfigDrive(final List<String[]> vmData, final String isoFileName, final String driveLabel) {
        if (vmData == null) {
            throw new CloudRuntimeException("No VM metadata provided");
        }

        Path tempDir = null;
        String tempDirName = null;
        try {
            tempDir = Files.createTempDirectory(ConfigDrive.CONFIGDRIVEDIR);
            tempDirName = tempDir.toString();

            File openStackFolder = new File(tempDirName + ConfigDrive.openStackConfigDriveName);
            if (openStackFolder.exists() || openStackFolder.mkdirs()) {
                writeFile(openStackFolder, "vendor_data.json", "{}");
                writeFile(openStackFolder, "network_data.json", "{}");
            } else {
                throw new CloudRuntimeException("Failed to create folder " + openStackFolder);
            }

            JsonObject metaData = new JsonObject();
            for (String[] item : vmData) {
                String dataType = item[CONFIGDATA_DIR];
                String fileName = item[CONFIGDATA_FILE];
                String content = item[CONFIGDATA_CONTENT];
                LOG.debug(String.format("[createConfigDriveIsoForVM] dataType=%s, filename=%s, content=%s",
                        dataType, fileName, (fileName.equals(PASSWORD_FILE)?"********":content)));

                // create file with content in folder
                if (dataType != null && !dataType.isEmpty()) {
                    //create folder
                    File typeFolder = new File(tempDirName + ConfigDrive.cloudStackConfigDriveName + dataType);
                    if (typeFolder.exists() || typeFolder.mkdirs()) {
                        if (StringUtils.isNotEmpty(content)) {
                            File file = new File(typeFolder, fileName + ".txt");
                            try  {
                                if (fileName.equals(USERDATA_FILE)) {
                                    // User Data is passed as a base64 encoded string
                                    FileUtils.writeByteArrayToFile(file, Base64.decodeBase64(content));
                                } else {
                                    FileUtils.write(file, content, com.cloud.utils.StringUtils.getPreferredCharset());
                                }
                            } catch (IOException ex) {
                                throw new CloudRuntimeException("Failed to create file ", ex);
                            }
                        }
                    } else {
                        throw new CloudRuntimeException("Failed to create folder: " + typeFolder);
                    }

                    //now write the file to the OpenStack directory
                    metaData = buildOpenStackMetaData(metaData, dataType, fileName, content);
                }
            }
            writeFile(openStackFolder, "meta_data.json", metaData.toString());

            String linkResult = linkUserData(tempDirName);
            if (linkResult != null) {
                String errMsg = "Unable to create user_data link due to " + linkResult;
                throw new CloudRuntimeException(errMsg);
            }

            File tmpIsoStore = new File(tempDirName, new File(isoFileName).getName());
            Script command = new Script("/usr/bin/genisoimage", Duration.standardSeconds(300), LOG);
            command.add("-o", tmpIsoStore.getAbsolutePath());
            command.add("-ldots");
            command.add("-allow-lowercase");
            command.add("-allow-multidot");
            command.add("-cache-inodes"); // Enable caching inode and device numbers to find hard links to files.
            command.add("-l");
            command.add("-quiet");
            command.add("-J");
            command.add("-r");
            command.add("-V", driveLabel);
            command.add(tempDirName);
            LOG.debug("Executing config drive creation command: " + command.toString());
            String result = command.execute();
            if (result != null) {
                String errMsg = "Unable to create iso file: " + isoFileName + " due to " + result;
                LOG.warn(errMsg);
                throw new CloudRuntimeException(errMsg);
            }
            File tmpIsoFile = new File(tmpIsoStore.getAbsolutePath());
            // Max allowed file size of config drive is 64MB: https://docs.openstack.org/project-install-guide/baremetal/draft/configdrive.html
            if (tmpIsoFile.length() > (64L * 1024L * 1024L)) {
                throw new CloudRuntimeException("Config drive file exceeds maximum allowed size of 64MB");
            }
            return fileToBase64String(tmpIsoFile);
        } catch (IOException e) {
            throw new CloudRuntimeException("Failed due to", e);
        } finally {
            try {
                FileUtils.deleteDirectory(tempDir.toFile());
            } catch (IOException ioe) {
                LOG.warn("Failed to delete ConfigDrive temporary directory: " + tempDirName, ioe);
            }
        }
    }
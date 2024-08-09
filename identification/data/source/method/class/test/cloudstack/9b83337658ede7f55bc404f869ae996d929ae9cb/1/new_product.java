public static String buildConfigDrive(List<String[]> vmData, String isoFileName, String driveLabel) {
        if (vmData == null) {
            throw new CloudRuntimeException("No VM metadata provided");
        }

        Path tempDir = null;
        String tempDirName = null;
        try {
            tempDir = Files.createTempDirectory(ConfigDrive.CONFIGDRIVEDIR);
            tempDirName = tempDir.toString();

            File openStackFolder = new File(tempDirName + ConfigDrive.openStackConfigDriveName);

            writeVendorAndNetworkEmptyJsonFile(openStackFolder);
            writeVmMetadata(vmData, tempDirName, openStackFolder);

            linkUserData(tempDirName);

            return generateAndRetrieveIsoAsBase64Iso(isoFileName, driveLabel, tempDirName);
        } catch (IOException e) {
            throw new CloudRuntimeException("Failed due to", e);
        } finally {
            deleteTempDir(tempDir);
        }
    }
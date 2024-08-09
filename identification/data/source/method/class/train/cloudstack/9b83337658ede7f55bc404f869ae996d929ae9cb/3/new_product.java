static void linkUserData(String tempDirName) {
        String userDataFilePath = tempDirName + ConfigDrive.cloudStackConfigDriveName + "userdata/user_data.txt";
        File file = new File(userDataFilePath);
        if (file.exists()) {
            Script hardLink = new Script("ln", Duration.standardSeconds(300), LOG);
            hardLink.add(userDataFilePath);
            hardLink.add(tempDirName + ConfigDrive.openStackConfigDriveName + "user_data");
            LOG.debug("execute command: " + hardLink.toString());

            String executionResult = hardLink.execute();
            if (StringUtils.isNotBlank(executionResult)) {
                throw new CloudRuntimeException("Unable to create user_data link due to " + executionResult);
            }
        }
    }
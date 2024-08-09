private static String linkUserData(String tempDirName) {
        //Hard link the user_data.txt file with the user_data file in the OpenStack directory.
        String userDataFilePath = tempDirName + ConfigDrive.cloudStackConfigDriveName + "userdata/user_data.txt";
        if ((new File(userDataFilePath).exists())) {
            Script hardLink = new Script("ln", Duration.standardSeconds(300), LOG);
            hardLink.add(userDataFilePath);
            hardLink.add(tempDirName + ConfigDrive.openStackConfigDriveName + "user_data");
            LOG.debug("execute command: " + hardLink.toString());
            return hardLink.execute();
        }
        return null;
    }
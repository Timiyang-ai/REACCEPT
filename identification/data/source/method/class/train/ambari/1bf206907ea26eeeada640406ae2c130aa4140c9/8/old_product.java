public boolean installAmbariServerIdentity(String principal,
                                             String srcKeytabFilePath,
                                             String destKeytabFilePath,
                                             ActionLog actionLog) throws AmbariException {

    // Use sudo to copy the file into place....
    try {
      ShellCommandUtil.Result result;

      // Ensure the parent directory exists...
      File destKeytabFile = new File(destKeytabFilePath);
      result = ShellCommandUtil.mkdir(destKeytabFile.getParent(), true);
      if (!result.isSuccessful()) {
        throw new AmbariException(result.getStderr());
      }

      // Copy the keytab file into place...
      result = ShellCommandUtil.copyFile(srcKeytabFilePath, destKeytabFilePath, true, true);
      if (!result.isSuccessful()) {
        throw new AmbariException(result.getStderr());
      } else {
        String ambariServerHostName = StageUtils.getHostName();
        HostEntity ambariServerHostEntity = hostDAO.findByName(ambariServerHostName);
        Long ambariServerHostID = (ambariServerHostEntity == null)
            ? null
            : ambariServerHostEntity.getHostId();

        if (ambariServerHostID == null) {
          String message = String.format("Failed to add the kerberos_principal_host record for %s on " +
                  "the Ambari server host since the host id for Ambari server host, %s, was not found." +
                  "  This is not an error if an Ambari agent is not installed on the Ambari server host.",
              principal, ambariServerHostName);
          LOG.warn(message);
          actionLog.writeStdErr(message);
        } else if (!kerberosPrincipalHostDAO.exists(principal, ambariServerHostID)) {
          kerberosPrincipalHostDAO.create(principal, ambariServerHostID);
        }

        actionLog.writeStdOut(String.format("Created Ambari server keytab file for %s at %s", principal, destKeytabFile));
      }
    } catch (InterruptedException | IOException e) {
      throw new AmbariException(e.getLocalizedMessage(), e);
    }

    return true;
  }
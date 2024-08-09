public boolean installAmbariServerIdentity(String principal,
                                             String srcKeytabFilePath,
                                             String destKeytabFilePath,
                                             String ownerName, boolean ownerReadable, boolean ownerWritable,
                                             String groupName, boolean groupReadable, boolean groupWritable,
                                             ActionLog actionLog) throws AmbariException {

    try {
      // Copy the keytab file into place (creating the parent directory, if necessary...
      copyFile(srcKeytabFilePath, destKeytabFilePath);
      setFileACL(destKeytabFilePath,
          ownerName, ownerReadable, ownerWritable,
          groupName, groupReadable, groupWritable);

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
        if (actionLog != null) {
          actionLog.writeStdErr(message);
        }
      } else if (!kerberosPrincipalHostDAO.exists(principal, ambariServerHostID)) {
        kerberosPrincipalHostDAO.create(principal, ambariServerHostID);
      }

      if (actionLog != null) {
        actionLog.writeStdOut(String.format("Created Ambari server keytab file for %s at %s", principal, destKeytabFilePath));
      }
    } catch (InterruptedException | IOException e) {
      throw new AmbariException(e.getLocalizedMessage(), e);
    }

    return true;
  }
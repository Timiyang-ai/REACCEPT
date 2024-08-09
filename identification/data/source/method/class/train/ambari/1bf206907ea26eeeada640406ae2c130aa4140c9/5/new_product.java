public boolean installAmbariServerIdentity(ResolvedKerberosPrincipal principal,
                                             String srcKeytabFilePath,
                                             String destKeytabFilePath,
                                             String ownerName, String ownerAccess,
                                             String groupName, String groupAccess,
                                             ActionLog actionLog) throws AmbariException {

    try {
      // Copy the keytab file into place (creating the parent directory, if necessary...
      boolean ownerWritable = "w".equalsIgnoreCase(ownerAccess) || "rw".equalsIgnoreCase(ownerAccess);
      boolean ownerReadable = "r".equalsIgnoreCase(ownerAccess) || "rw".equalsIgnoreCase(ownerAccess);
      boolean groupWritable = "w".equalsIgnoreCase(groupAccess) || "rw".equalsIgnoreCase(groupAccess);
      boolean groupReadable = "r".equalsIgnoreCase(groupAccess) || "rw".equalsIgnoreCase(groupAccess);

      copyFile(srcKeytabFilePath, destKeytabFilePath);
      setFileACL(destKeytabFilePath,
          ownerName, ownerReadable, ownerWritable,
          groupName, groupReadable, groupWritable);

      Long ambariServerHostID = ambariServerHostID();
      HostEntity hostEntity = null;
      if (ambariServerHostID != null) {
        hostEntity = hostDAO.findById(ambariServerHostID);
      }

      KerberosKeytabEntity kke = kerberosKeytabDAO.find(destKeytabFilePath);
      if (kke == null) {
        kke = new KerberosKeytabEntity(destKeytabFilePath);
        kke.setOwnerName(ownerName);
        kke.setOwnerAccess(ownerAccess);
        kke.setGroupName(groupName);
        kke.setGroupAccess(groupAccess);
        kerberosKeytabDAO.create(kke);
      }

      KerberosPrincipalEntity kpe = kerberosPrincipalDAO.find(principal.getPrincipal());
      if(kpe == null) {
        kpe = new KerberosPrincipalEntity(principal.getPrincipal(), principal.isService(), principal.getCacheFile());
        kerberosPrincipalDAO.create(kpe);
      }

      for(Map.Entry<String, String> mapping : principal.getServiceMapping().entries()) {
        String serviceName = mapping.getKey();
        String componentName = mapping.getValue();
        KerberosKeytabPrincipalEntity entity = kerberosKeytabPrincipalDAO.findOrCreate(kke, hostEntity, kpe).kkp;
        entity.setDistributed(true);
        entity.putServiceMapping(serviceName, componentName);
        kerberosKeytabPrincipalDAO.merge(entity);

        kke.addKerberosKeytabPrincipal(entity);
        kerberosKeytabDAO.merge(kke);

        kpe.addKerberosKeytabPrincipal(entity);
        kerberosPrincipalDAO.merge(kpe);
      }

      if (actionLog != null) {
        actionLog.writeStdOut(String.format("Created Ambari server keytab file for %s at %s", principal, destKeytabFilePath));
      }
    } catch (InterruptedException | IOException e) {
      throw new AmbariException(e.getLocalizedMessage(), e);
    }

    return true;
  }
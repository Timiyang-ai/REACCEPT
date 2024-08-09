public void configureJAAS(String principal, String keytabFilePath, ActionLog actionLog) {
    String jaasConfPath = getJAASConfFilePath();
    if (jaasConfPath != null) {
      File jaasConfigFile = new File(jaasConfPath);
      try {
        String jaasConfig = FileUtils.readFileToString(jaasConfigFile, Charset.defaultCharset());
        File oldJaasConfigFile = new File(jaasConfPath + ".bak");
        FileUtils.writeStringToFile(oldJaasConfigFile, jaasConfig, Charset.defaultCharset());
        jaasConfig = jaasConfig.replaceFirst(KEYTAB_PATTERN, "keyTab=\"" + keytabFilePath + "\"");
        jaasConfig = jaasConfig.replaceFirst(PRINCIPAL_PATTERN, "principal=\"" + principal + "\"");
        FileUtils.writeStringToFile(jaasConfigFile, jaasConfig, Charset.defaultCharset());
        String message = String.format("JAAS config file %s modified successfully for principal %s.",
            jaasConfigFile.getName(), principal);
        if (actionLog != null) {
          actionLog.writeStdOut(message);
        }
      } catch (IOException e) {
        String message = String.format("Failed to configure JAAS file %s for %s - %s",
            jaasConfigFile, principal, e.getMessage());
        if (actionLog != null) {
          actionLog.writeStdErr(message);
        }
        LOG.error(message, e);
      }
    } else {
      String message = String.format("Failed to configure JAAS, config file should be passed to Ambari server as: " +
          "%s.", KerberosChecker.JAVA_SECURITY_AUTH_LOGIN_CONFIG);
      if (actionLog != null) {
        actionLog.writeStdErr(message);
      }
      LOG.error(message);
    }
  }
public static boolean isKerberosEnabled(CConfiguration cConf) {
    return cConf.getBoolean(Constants.Security.KERBEROS_ENABLED,
                            cConf.getBoolean(Constants.Security.CFG_SECURITY_ENABLED));
  }
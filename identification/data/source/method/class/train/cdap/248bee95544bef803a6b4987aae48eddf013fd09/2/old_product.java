public static boolean isKerberosEnabled(CConfiguration cConf) {
    return cConf.get(Constants.Security.CFG_CDAP_MASTER_KRB_PRINCIPAL) != null &&
      cConf.get(Constants.Security.CFG_CDAP_MASTER_KRB_KEYTAB_PATH) != null;
  }
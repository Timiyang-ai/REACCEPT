  @Test
  public void isKerberosEnabled() throws Exception {
    CConfiguration kerbConf = CConfiguration.create();
    kerbConf.set(Constants.Security.KERBEROS_ENABLED, "true");
    kerbConf.set(Constants.Security.CFG_CDAP_MASTER_KRB_PRINCIPAL, "prinicpal@REALM.NET");
    kerbConf.set(Constants.Security.CFG_CDAP_MASTER_KRB_KEYTAB_PATH, "/path/to/keytab");
    Assert.assertTrue(SecurityUtil.isKerberosEnabled(kerbConf));

    CConfiguration noPrincipalConf = CConfiguration.create();
    kerbConf.set(Constants.Security.KERBEROS_ENABLED, "false");
    noPrincipalConf.unset(Constants.Security.CFG_CDAP_MASTER_KRB_PRINCIPAL);
    noPrincipalConf.set(Constants.Security.CFG_CDAP_MASTER_KRB_KEYTAB_PATH, "/path/to/keytab");
    Assert.assertFalse(SecurityUtil.isKerberosEnabled(noPrincipalConf));

    CConfiguration noKeyTabConf = CConfiguration.create();
    kerbConf.set(Constants.Security.KERBEROS_ENABLED, "false");
    noKeyTabConf.unset(Constants.Security.CFG_CDAP_MASTER_KRB_KEYTAB_PATH);
    noKeyTabConf.set(Constants.Security.CFG_CDAP_MASTER_KRB_PRINCIPAL, "prinicpal@REALM.NET");
    Assert.assertFalse(SecurityUtil.isKerberosEnabled(noKeyTabConf));
  }
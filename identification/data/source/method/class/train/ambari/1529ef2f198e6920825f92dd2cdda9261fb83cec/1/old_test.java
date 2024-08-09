  @Test
  public void isPassword() {
    Assert.assertEquals(TPROXY_AUTHENTICATION_ENABLED.getConfigurationPropertyType() == PASSWORD,
        AmbariServerConfigurationUtils.isPassword(TPROXY_AUTHENTICATION_ENABLED.getConfigurationCategory(), TPROXY_AUTHENTICATION_ENABLED.key()));
    Assert.assertEquals(TPROXY_AUTHENTICATION_ENABLED.getConfigurationPropertyType() == PASSWORD,
        AmbariServerConfigurationUtils.isPassword(TPROXY_AUTHENTICATION_ENABLED.getConfigurationCategory().getCategoryName(), TPROXY_AUTHENTICATION_ENABLED.key()));

    // Test Regex Key
    Assert.assertEquals(TPROXY_ALLOWED_GROUPS.getConfigurationPropertyType() == PASSWORD,
        AmbariServerConfigurationUtils.isPassword(TPROXY_ALLOWED_GROUPS.getConfigurationCategory().getCategoryName(), TPROXY_ALLOWED_GROUPS.key()));
    Assert.assertEquals(TPROXY_ALLOWED_GROUPS.getConfigurationPropertyType() == PASSWORD,
        AmbariServerConfigurationUtils.isPassword(TPROXY_ALLOWED_GROUPS.getConfigurationCategory().getCategoryName(), "ambari.tproxy.proxyuser.knox.groups"));
    Assert.assertEquals(TPROXY_ALLOWED_GROUPS.getConfigurationPropertyType() == PASSWORD,
        AmbariServerConfigurationUtils.isPassword(TPROXY_ALLOWED_GROUPS.getConfigurationCategory().getCategoryName(), "ambari.tproxy.proxyuser.not.knox.groups"));

    Assert.assertFalse(AmbariServerConfigurationUtils.isPassword(TPROXY_ALLOWED_GROUPS.getConfigurationCategory().getCategoryName(), "invalid.tproxy.proxyuser.not.knox.groups"));

    Assert.assertFalse(AmbariServerConfigurationUtils.isPassword((AmbariServerConfigurationCategory) null, TPROXY_AUTHENTICATION_ENABLED.key()));
    Assert.assertFalse(AmbariServerConfigurationUtils.isPassword((String) null, TPROXY_AUTHENTICATION_ENABLED.key()));
    Assert.assertFalse(AmbariServerConfigurationUtils.isPassword("invalid", TPROXY_AUTHENTICATION_ENABLED.key()));

    Assert.assertFalse(AmbariServerConfigurationUtils.isPassword(TPROXY_CONFIGURATION.getCategoryName(), null));
    Assert.assertFalse(AmbariServerConfigurationUtils.isPassword(TPROXY_CONFIGURATION.getCategoryName(), "invalid"));

    // This is known to be a password
    Assert.assertTrue(AmbariServerConfigurationUtils.isPassword(BIND_PASSWORD));
  }
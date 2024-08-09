@Override
  public UpgradeCheckResult perform(UpgradeCheckRequest request) throws AmbariException {
    UpgradeCheckResult result = new UpgradeCheckResult(this, UpgradeCheckStatus.PASS);

    String autoStartEnabled = getProperty(request, CLUSTER_ENV_TYPE, RECOVERY_ENABLED_KEY);

    // !!! auto-start is already disabled
    if (!Boolean.valueOf(autoStartEnabled)) {
      return result;
    }

    // !!! double check the value is AUTO_START.  it's the only supported value (and there's no enum for it)
    String recoveryType = getProperty(request, CLUSTER_ENV_TYPE, RECOVERY_TYPE_KEY);
    if (StringUtils.equals(recoveryType, RECOVERY_AUTO_START)) {

      result.setFailReason(getFailReason(result, request));
      result.setStatus(UpgradeCheckStatus.FAIL);
      result.getFailedOn().add(request.getClusterInformation().getClusterName());
    }

    return result;
  }
@Override
  public void perform(PrerequisiteCheck prerequisiteCheck, PrereqCheckRequest request) throws AmbariException {

    String autoStartEnabled = getProperty(request, CLUSTER_ENV_TYPE, RECOVERY_ENABLED_KEY);

    // !!! auto-start is already disabled
    if (!Boolean.valueOf(autoStartEnabled)) {
      return;
    }

    // !!! double check the value is AUTO_START.  it's the only supported value (and there's no enum for it)
    String recoveryType = getProperty(request, CLUSTER_ENV_TYPE, RECOVERY_TYPE_KEY);
    if (StringUtils.equals(recoveryType, RECOVERY_AUTO_START)) {

      prerequisiteCheck.setFailReason(getFailReason(prerequisiteCheck, request));
      prerequisiteCheck.setStatus(PrereqCheckStatus.FAIL);
      prerequisiteCheck.getFailedOn().add(request.getClusterName());

    }
  }
public boolean isIntegratedSecurity() {
    if (isIntegratedSecurity != null) {
      return isIntegratedSecurity;
    }

    try {
      isIntegratedSecurity = (SecurityUtils.getSecurityManager() != null);
    } catch (UnavailableSecurityManagerException e) {
      isIntegratedSecurity = false;
    }
    return isIntegratedSecurity;
  }
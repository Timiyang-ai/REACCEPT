public Set<AlertDefinition> getAlertDefinitions(String stackName, String stackVersion,
      String serviceName) throws AmbariException {

    ServiceInfo svc = getService(stackName, stackVersion, serviceName);
    File alertsFile = svc.getAlertsFile();

    if (null == alertsFile || !alertsFile.exists()) {
      LOG.debug("Alerts file for " + stackName + "/" + stackVersion + "/" + serviceName + " not found.");
      return null;
    }

    Set<AlertDefinition> defs = new HashSet<AlertDefinition>();
    Map<String, List<AlertDefinition>> map = alertDefinitionFactory.getAlertDefinitions(alertsFile);

    for (Entry<String, List<AlertDefinition>> entry : map.entrySet()) {
      for (AlertDefinition ad : entry.getValue()) {
        ad.setServiceName(serviceName);
        if (!entry.getKey().equals("service")) {
          ad.setComponentName(entry.getKey());
        }
      }
      defs.addAll(entry.getValue());
    }

    return defs;
  }
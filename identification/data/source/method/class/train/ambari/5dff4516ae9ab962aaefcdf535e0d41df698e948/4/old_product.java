public boolean isOperationAllowed(Resource.Type sourceType,
                                    ServiceComponentHost sch) throws AmbariException {
    MaintenanceState maintenanceState = sch.getMaintenanceState();

    if (sourceType.equals(Resource.Type.Cluster)) {

      if (maintenanceState.equals(MaintenanceState.OFF)) {
        return true;
      }

    } else if (sourceType.equals(Resource.Type.Service)) {

      if (maintenanceState.equals(MaintenanceState.IMPLIED_FROM_SERVICE)
          || maintenanceState.equals(MaintenanceState.OFF)) {
        return true;
      }

    } else if (sourceType.equals(Resource.Type.Host)) {

      if (maintenanceState.equals(MaintenanceState.IMPLIED_FROM_HOST)
          || maintenanceState.equals(MaintenanceState.OFF)) {
        return true;
      }

    } else if (sourceType.equals(Resource.Type.HostComponent)) {

      return true;

    } else {
      LOG.warn("Unsupported Resource type, type = " + sourceType);
    }

    return false;
  }
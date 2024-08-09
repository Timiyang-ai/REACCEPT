public boolean isOperationAllowed(Resource.Type operationLevel,
                                    ServiceComponentHost sch) throws AmbariException {
    MaintenanceState maintenanceState = sch.getMaintenanceState();

    switch (operationLevel.getInternalType()) {
      case Cluster:
          if (maintenanceState.equals(MaintenanceState.OFF)) {
            return true;
          }
          break;
      case Service:
        if (maintenanceState.equals(MaintenanceState.IMPLIED_FROM_SERVICE)
                || maintenanceState.equals(MaintenanceState.OFF)) {
          return true;
        }
        break;
      case Host:
        if (maintenanceState.equals(MaintenanceState.IMPLIED_FROM_HOST)
                || maintenanceState.equals(MaintenanceState.OFF)) {
          return true;
        }
        break;
      case HostComponent: {
        return true;
      }
      default:
        LOG.warn("Unsupported Resource type, type = " + operationLevel);
        break;
    }
    return false;
  }
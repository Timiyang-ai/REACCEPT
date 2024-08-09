@Override
  public RequestStatus updateResources(Request request, Predicate predicate)
      throws SystemException, UnsupportedPropertyException,
      NoSuchResourceException, NoSuchParentResourceException {

    // check the predicate to see if there is a reques to run
    // the alert definition immediately
    if( null != predicate ){
      Set<Map<String,Object>> predicateMaps = getPropertyMaps(predicate);
      for (Map<String, Object> propertyMap : predicateMaps) {
        String runNow = (String) propertyMap.get(ALERT_DEF_ACTION_RUN_NOW);
        if (null != runNow) {
          if (Boolean.valueOf(runNow) == Boolean.TRUE) {
            scheduleImmediateAlert(propertyMap);
          }
        }
      }
    }

    // if an AlertDefinition property body was specified, perform the update
    for (Map<String, Object> requestPropMap : request.getProperties()) {
      for (Map<String, Object> propertyMap : getPropertyMaps(requestPropMap, predicate)) {
        String stringId = (String) propertyMap.get(ALERT_DEF_ID);
        long id = Long.parseLong(stringId);

        AlertDefinitionEntity entity = alertDefinitionDAO.findById(id);
        if (null == entity) {
          continue;
        }

        // capture the state of the old entity
        boolean oldEnabled = entity.getEnabled();

        try {
          populateEntity(entity, propertyMap);
          alertDefinitionDAO.merge(entity);

          // invalidate and publish the definition hash
          Set<String> invalidatedHosts = alertDefinitionHash.invalidateHosts(entity);
          AlertHashInvalidationEvent event = new AlertHashInvalidationEvent(
              entity.getClusterId(), invalidatedHosts);

          eventPublisher.publish(event);
        } catch (AmbariException ae) {
          LOG.error("Unable to find cluster when updating alert definition", ae);
        }

        // if the old state was enabled and the new state is not, trigger
        // a disabled event
        if (oldEnabled && !entity.getEnabled()) {
          AlertDefinitionDisabledEvent event = new AlertDefinitionDisabledEvent(
              entity.getClusterId(), entity.getDefinitionId());

          eventPublisher.publish(event);
        }
      }
    }

    notifyUpdate(Resource.Type.AlertDefinition, request, predicate);
    return getRequestStatus(null);
  }
@Override
  public RequestStatus deleteResources(Request request, Predicate predicate)
      throws SystemException, UnsupportedPropertyException,
      NoSuchResourceException, NoSuchParentResourceException {

    Set<Resource> resources = getResources(
        new RequestImpl(null, null, null, null), predicate);

    Set<Long> definitionIds = new HashSet<Long>();

    for (final Resource resource : resources) {
      Long id = (Long) resource.getPropertyValue(ALERT_DEF_ID);
      definitionIds.add(id);
    }

    for (Long definitionId : definitionIds) {
      LOG.info("Deleting alert definition {}", definitionId);

      final AlertDefinitionEntity entity = alertDefinitionDAO.findById(definitionId.longValue());

      AlertResourceProviderUtils.verifyManageAuthorization(entity);

      modifyResources(new Command<Void>() {
        @Override
        public Void invoke() throws AmbariException {
          long clusterId = entity.getClusterId();

          // remove the entity
          alertDefinitionDAO.remove(entity);

          // publish the hash invalidation
          final Set<String> invalidatedHosts = alertDefinitionHash.invalidateHosts(entity);
          AlertHashInvalidationEvent event = new AlertHashInvalidationEvent(
              clusterId, invalidatedHosts);

          eventPublisher.publish(event);

          return null;
        }
      });
    }

    notifyDelete(Resource.Type.AlertDefinition, predicate);
    return getRequestStatus(null);
  }
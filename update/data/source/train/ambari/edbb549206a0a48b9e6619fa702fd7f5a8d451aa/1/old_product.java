public RequestStatus createResources(final Set<ConfigGroupRequest> requests)throws
      SystemException, UnsupportedPropertyException,
      ResourceAlreadyExistsException, NoSuchParentResourceException{

    Set<ConfigGroupResponse> responses =
        createResources(new Command<Set<ConfigGroupResponse>>() {
          @Override
          public Set<ConfigGroupResponse> invoke() throws AmbariException, AuthorizationException {
            return createConfigGroups(requests);
          }
        });

    Set<Resource> associatedResources = new HashSet<Resource>();
    for (ConfigGroupResponse response : responses) {
      Resource resource = new ResourceImpl(Resource.Type.ConfigGroup);
      resource.setProperty(CONFIGGROUP_ID_PROPERTY_ID, response.getId());
      associatedResources.add(resource);
    }

    return getRequestStatus(null, associatedResources);
  }
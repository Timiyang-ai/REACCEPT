  private void processHostGroupComponents(TreeNode<Resource> hostNode, Collection<String> components) {
    Resource hostComponentsResource = new ResourceImpl(Resource.Type.HostComponent);
    TreeNode<Resource> hostComponentsNode = hostNode.addChild(hostComponentsResource, "host_components");
    int componentCount = 1;
    for (String component : components) {
      Resource componentResource = new ResourceImpl(Resource.Type.HostComponent);
      componentResource.setProperty("HostRoles/component_name", component);
      hostComponentsNode.addChild(componentResource, "host_component_" + componentCount++);
    }
  }
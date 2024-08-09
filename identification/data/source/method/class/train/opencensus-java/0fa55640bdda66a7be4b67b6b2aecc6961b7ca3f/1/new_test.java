  @Test
  public void create_K8sContainerResourceTest() {
    Resource resource =
        K8sResource.create(K8S_CLUSTER_NAME, K8S_NAMESPACE_NAME, K8S_POD_NAME, K8S_DEPLOYMENT_NAME);
    assertThat(resource.getType()).isEqualTo(K8sResource.TYPE);
    assertThat(resource.getLabels())
        .containsExactly(
            K8sResource.CLUSTER_NAME_KEY,
            K8S_CLUSTER_NAME,
            K8sResource.NAMESPACE_NAME_KEY,
            K8S_NAMESPACE_NAME,
            K8sResource.POD_NAME_KEY,
            K8S_POD_NAME,
            K8sResource.DEPLOYMENT_NAME_KEY,
            K8S_DEPLOYMENT_NAME);
  }
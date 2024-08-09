public List<TenantDto> getTenants() throws Exception {
    ResponseEntity<List<TenantDto>> entity = restTemplate.exchange(
        restTemplate.getUrl() + "tenants",
        HttpMethod.GET, null, new ParameterizedTypeReference<List<TenantDto>>() {});
    return entity.getBody();
  }
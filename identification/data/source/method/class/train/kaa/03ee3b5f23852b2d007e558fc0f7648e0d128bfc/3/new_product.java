public List<UserDto> getAllTenantAdminsByTenantId(String tenantId) {
    ResponseEntity<List<UserDto>> entity = restTemplate.exchange(
        restTemplate.getUrl() + "admins/" + tenantId,
        HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDto>>() {});
    return entity.getBody();
  }
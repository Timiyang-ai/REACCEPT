public List<EventClassDto> getEventClassesByFamilyIdVersionAndType(String eventClassFamilyId,
                                                                     int version,
                                                                     EventClassType type)
      throws Exception {
    ResponseEntity<List<EventClassDto>> entity = restTemplate.exchange(restTemplate.getUrl()
        + "eventClasses?eventClassFamilyId={eventClassFamilyId}&"
        + "version={version}"
        + "&type={type}",
        HttpMethod.GET, null,  new ParameterizedTypeReference<List<EventClassDto>>() {},
        eventClassFamilyId, version, type);
    return entity.getBody();
  }
public List<EventClassDto> getEventClassesByFamilyIdVersionAndType(String eventClassFamilyId, int version, EventClassType type)
            throws Exception {
        ParameterizedTypeReference<List<EventClassDto>> typeRef = new ParameterizedTypeReference<List<EventClassDto>>() {
        };
        ResponseEntity<List<EventClassDto>> entity = restTemplate.exchange(restTemplate.getUrl()
                        + "eventClasses?eventClassFamilyId={eventClassFamilyId}&version={version}&type={type}", HttpMethod.GET, null, typeRef,
                eventClassFamilyId, version, type);
        return entity.getBody();
    }
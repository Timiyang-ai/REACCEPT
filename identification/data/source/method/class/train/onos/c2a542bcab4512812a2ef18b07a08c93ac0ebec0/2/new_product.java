@DELETE
    @Path("{subjectClassKey}")
    @SuppressWarnings("unchecked")
    public void delete(@PathParam("subjectClassKey") String subjectClassKey) {
        NetworkConfigService service = get(NetworkConfigService.class);
        service.getSubjects(service.getSubjectFactory(subjectClassKey).subjectClass())
                .forEach(subject -> service.removeConfig(subject));
    }
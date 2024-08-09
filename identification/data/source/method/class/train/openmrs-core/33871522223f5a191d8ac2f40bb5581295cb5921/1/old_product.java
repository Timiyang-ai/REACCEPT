@Override
    @Transactional(readOnly = true)
    public List<Concept> getDrugRoutes(){
        String conceptUuid = Context.getAdministrationService().getGlobalProperty(OpenmrsConstants.DRUG_ROUTE_CONCEPT_UUID);
        return Context.getConceptService().getConceptByUuid(conceptUuid).getSetMembers();
    }
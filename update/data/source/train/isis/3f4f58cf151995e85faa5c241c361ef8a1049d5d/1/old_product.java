@Override
    public ClearAssociationResponse clearAssociation(final ClearAssociationRequest request) {

        AuthenticationSession session = request.getSession();
        String fieldIdentifier = request.getFieldIdentifier();
        IdentityData targetData = request.getTarget();
        IdentityData associateData = request.getAssociate();

        if (LOG.isDebugEnabled()) {
            LOG.debug("request clearAssociation " + fieldIdentifier + " on " + targetData + " of " + associateData
                + " for " + session);
        }

        final ObjectAdapter targetAdapter = getPersistentObjectAdapter(session, targetData);
        final ObjectAdapter associateAdapter = getPersistentObjectAdapter(session, associateData);
        final ObjectSpecification specification = targetAdapter.getSpecification();
        final ObjectAssociation association = specification.getAssociation(fieldIdentifier);

        if (!association.isVisible(session, targetAdapter).isAllowed()
            || association.isUsable(session, targetAdapter).isVetoed()) {
            throw new IsisException("can't modify field as not visible or editable");
        }
        ensureAssociationModifiableElseThrowException(session, targetAdapter, association);

        if (association instanceof OneToOneAssociation) {
            ((OneToOneAssociation) association).clearAssociation(targetAdapter);
        } else {
            ((OneToManyAssociation) association).removeElement(targetAdapter, associateAdapter);
        }
        return new ClearAssociationResponse(getUpdates());
    }
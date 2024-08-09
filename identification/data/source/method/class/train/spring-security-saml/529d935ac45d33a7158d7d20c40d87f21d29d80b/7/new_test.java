    @Test
    public void verifySubject() throws Exception {

        SubjectConfirmationData subjectConfirmationData = ((SAMLObjectBuilder<SubjectConfirmationData>) builderFactory.getBuilder(SubjectConfirmationData.DEFAULT_ELEMENT_NAME)).buildObject();
        subjectConfirmationData.setNotOnOrAfter(new DateTime().minusSeconds(10));
        subjectConfirmationData.setRecipient("https://www.test.local/SSO");

        SAMLObjectBuilder<SubjectConfirmation> subjectConfirmationBuilder = (SAMLObjectBuilder<SubjectConfirmation>) builderFactory.getBuilder(SubjectConfirmation.DEFAULT_ELEMENT_NAME);
        SubjectConfirmation subjectConfirmation = subjectConfirmationBuilder.buildObject();
        subjectConfirmation.setMethod(SubjectConfirmation.METHOD_BEARER);
        subjectConfirmation.setSubjectConfirmationData(subjectConfirmationData);

        SAMLObjectBuilder<Subject> subjectBuilder = (SAMLObjectBuilder<Subject>) builderFactory.getBuilder(Subject.DEFAULT_ELEMENT_NAME);
        Subject subject = subjectBuilder.buildObject();
        subject.getSubjectConfirmations().add(subjectConfirmation);

        profile.verifySubject(subject, null, messageContext);

    }
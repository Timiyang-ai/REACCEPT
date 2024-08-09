protected void verifySubject(Subject subject, AuthnRequest request, SAMLMessageContext context) throws SAMLException, DecryptionException {
        boolean confirmed = false;

        for (SubjectConfirmation confirmation : subject.getSubjectConfirmations()) {
            if (SubjectConfirmation.METHOD_BEARER.equals(confirmation.getMethod())) {

                SubjectConfirmationData data = confirmation.getSubjectConfirmationData();

                // Bearer must have confirmation 554
                if (data == null) {
                    log.debug("Assertion invalidated by missing confirmation data");
                    throw new SAMLException("SAML Assertion is invalid");
                }

                // Not before forbidden by core 558
                if (data.getNotBefore() != null) {
                    log.debug("Assertion contains not before in bearer confirmation, which is forbidden");
                    throw new SAMLException("SAML Assertion is invalid");
                }

                // Validate not on or after
                if (data.getNotOnOrAfter().isBeforeNow()) {
                    log.debug("Invalidated by notOnOrAfter");
                    confirmed = false;
                    continue;
                }

                // Validate in response to
                if (request != null) {
                    if (data.getInResponseTo() == null) {
                        log.debug("Assertion invalidated by subject confirmation - missing inResponseTo field");
                        throw new SAMLException("SAML Assertion is invalid");
                    } else {
                        if (!data.getInResponseTo().equals(request.getID())) {
                            log.debug("Assertion invalidated by subject confirmation - invalid in response to");
                            throw new SAMLException("SAML Assertion is invalid");
                        }
                    }
                }

                // Validate recipient
                if (data.getRecipient() == null) {
                    log.debug("Assertion invalidated by subject confirmation - recipient is missing in bearer confirmation");
                    throw new SAMLException("SAML Assertion is invalid");
                } else {
                    SPSSODescriptor spssoDescriptor = (SPSSODescriptor) context.getLocalEntityRoleMetadata();
                    for (AssertionConsumerService service : spssoDescriptor.getAssertionConsumerServices()) {
                        if (context.getCommunicationProfileId().equals(service.getBinding()) && service.getLocation().equals(data.getRecipient())) {
                            confirmed = true;
                        }
                    }
                }
            }

            // Was the subject confirmed by this confirmation data? If so let's store the subject in context.
            if (confirmed) {
                NameID nameID;
                if (subject.getEncryptedID() != null) {
                    Assert.notNull(context.getLocalDecrypter(), "Can't decrypt NameID, no decrypter is set in the context");
                    nameID = (NameID) context.getLocalDecrypter().decrypt(subject.getEncryptedID());
                } else {
                    nameID = subject.getNameID();
                }
                context.setSubjectNameIdentifier(nameID);
                return;
            }
        }

        log.debug("Assertion invalidated by subject confirmation - can't be confirmed by bearer method");
        throw new SAMLException("SAML Assertion is invalid");
    }
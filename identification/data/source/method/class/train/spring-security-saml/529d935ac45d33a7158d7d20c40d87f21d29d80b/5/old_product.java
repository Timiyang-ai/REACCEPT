protected void verifySubject(Subject subject, AuthnRequest request, SAMLMessageContext context) throws SAMLException, DecryptionException {

        for (SubjectConfirmation confirmation : subject.getSubjectConfirmations()) {

            if (SubjectConfirmation.METHOD_BEARER.equals(confirmation.getMethod())) {

                log.debug("Processing Bearer subject confirmation");
                SubjectConfirmationData data = confirmation.getSubjectConfirmationData();

                // Bearer must have confirmation 554
                if (data == null) {
                    log.debug("Bearer SubjectConfirmation invalidated by missing confirmation data");
                    continue;
                }

                // Not before forbidden by core 558
                if (data.getNotBefore() != null) {
                    log.debug("Bearer SubjectConfirmation invalidated by not before which is forbidden");
                    continue;
                }

                // Validate not on or after
                if (data.getNotOnOrAfter().isBeforeNow()) {
                    log.debug("Bearer SubjectConfirmation invalidated by notOnOrAfter");
                    continue;
                }

                // Validate in response to
                if (request != null) {
                    if (data.getInResponseTo() == null) {
                        log.debug("Bearer SubjectConfirmation invalidated by missing inResponseTo field");
                        continue;
                    } else {
                        if (!data.getInResponseTo().equals(request.getID())) {
                            log.debug("Bearer SubjectConfirmation invalidated by invalid in response to");
                            continue;
                        }
                    }
                }

                // Validate recipient
                if (data.getRecipient() == null) {
                    log.debug("Bearer SubjectConfirmation invalidated by missing recipient");
                    continue;
                } else {
                    try {
                        verifyEndpoint(context.getLocalEntityEndpoint(), data.getRecipient());
                    } catch (SAMLException e) {
                        log.debug("Bearer SubjectConfirmation invalidated by recipient assertion consumer URL, found {}", data.getRecipient());
                        continue;
                    }
                }

                // Was the subject confirmed by this confirmation data? If so let's store the subject in the context.
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

        log.debug("Assertion invalidated by subject confirmation - can't be confirmed by the bearer method");
        throw new SAMLException("SAML Assertion is invalid");

    }
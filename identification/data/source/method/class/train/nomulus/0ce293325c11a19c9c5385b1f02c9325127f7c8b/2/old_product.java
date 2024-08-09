private void validateCertificate(Registrar registrar) throws AuthenticationErrorException {
    if (isNullOrEmpty(registrar.getClientCertificateHash())
        && isNullOrEmpty(registrar.getFailoverClientCertificateHash())) {
      logger.infofmt(
          "Skipping SSL certificate check because %s doesn't have any certificate hashes on file",
          registrar.getClientIdentifier());
      return;
    }
    if (isNullOrEmpty(clientCertificateHash)) {
      // If there's no SNI header that's probably why we don't have a cert, so send a specific
      // message. Otherwise, send a missing certificate message.
      if (!hasSni()) {
        throw new NoSniException();
      }
      logger.infofmt("Request did not include %s", EppTlsServlet.SSL_CLIENT_CERTIFICATE_HASH_FIELD);
      throw new MissingRegistrarCertificateException();
    }
    if (!clientCertificateHash.equals(registrar.getClientCertificateHash())
        && !clientCertificateHash.equals(registrar.getFailoverClientCertificateHash())) {
      logger.warningfmt("bad certificate hash (%s) for %s, wanted either %s or %s",
          clientCertificateHash,
          registrar.getClientIdentifier(),
          registrar.getClientCertificateHash(),
          registrar.getFailoverClientCertificateHash());
      throw new BadRegistrarCertificateException();
    }
  }
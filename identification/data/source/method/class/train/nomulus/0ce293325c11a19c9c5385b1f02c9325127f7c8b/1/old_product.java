private void validateCertificate(Registrar registrar) throws AuthenticationErrorException {
    if (isNullOrEmpty(registrar.getClientCertificateHash())
        && isNullOrEmpty(registrar.getFailoverClientCertificateHash())) {
      logger.atInfo().log(
          "Skipping SSL certificate check because %s doesn't have any certificate hashes on file",
          registrar.getClientId());
      return;
    }
    if (isNullOrEmpty(clientCertificateHash)) {
      // If there's no SNI header that's probably why we don't have a cert, so send a specific
      // message. Otherwise, send a missing certificate message.
      if (!hasSni()) {
        throw new NoSniException();
      }
      logger.atInfo().log("Request did not include X-SSL-Certificate");
      throw new MissingRegistrarCertificateException();
    }
    if (!clientCertificateHash.equals(registrar.getClientCertificateHash())
        && !clientCertificateHash.equals(registrar.getFailoverClientCertificateHash())) {
      logger.atWarning().log(
          "bad certificate hash (%s) for %s, wanted either %s or %s",
          clientCertificateHash,
          registrar.getClientId(),
          registrar.getClientCertificateHash(),
          registrar.getFailoverClientCertificateHash());
      throw new BadRegistrarCertificateException();
    }
  }
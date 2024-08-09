private void validateCertificate(Registrar registrar) throws AuthenticationErrorException {
    if (isNullOrEmpty(registrar.getClientCertificateHash())
        && isNullOrEmpty(registrar.getFailoverClientCertificateHash())) {
      logger.atInfo().log(
          "Skipping SSL certificate check because %s doesn't have any certificate hashes on file",
          registrar.getClientId());
      return;
    }
    if (isNullOrEmpty(clientCertificateHash)) {
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
@VisibleForTesting
  void validateCertificate(Registrar registrar) throws AuthenticationErrorException {
    if (isNullOrEmpty(registrar.getClientCertificateHash())
        && isNullOrEmpty(registrar.getFailoverClientCertificateHash())) {
      if (requireSslCertificates) {
        throw new RegistrarCertificateNotConfiguredException();
      } else {
        // If the environment is configured to allow missing SSL certificate hashes and this hash is
        // missing, then bypass the certificate hash checks.
        return;
      }
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
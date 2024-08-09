public PagedList<CertificateItem> listCertificates(final String vaultBaseUrl, final Integer maxresults) {
        return innerKeyVaultClient.getCertificates(vaultBaseUrl, maxresults);
    }
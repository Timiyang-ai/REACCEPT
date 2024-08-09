public ServiceResponse<PagedList<CertificateItem>> listCertificateVersions(final String vaultBaseUrl, final String certificateName, final Integer maxresults)
            throws KeyVaultErrorException, IOException, IllegalArgumentException {
        return innerKeyVaultClient.getCertificateVersions(vaultBaseUrl, certificateName, maxresults);
    }
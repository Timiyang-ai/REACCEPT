public PagedList<CertificateItem> listCertificateVersions(final String vaultBaseUrl, final String certificateName) {
        return innerKeyVaultClient.getCertificateVersions(vaultBaseUrl, certificateName);
    }
public PagedList<CertificateItem> listCertificates(final String vaultBaseUrl) {
        return innerKeyVaultClient.getCertificates(vaultBaseUrl);
    }
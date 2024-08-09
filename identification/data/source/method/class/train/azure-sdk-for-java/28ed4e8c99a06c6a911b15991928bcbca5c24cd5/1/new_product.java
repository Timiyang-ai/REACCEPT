public PagedList<CertificateItem> listCertificateVersions(final String vaultBaseUrl, final String certificateName) 
            throws KeyVaultErrorException, IOException, IllegalArgumentException {
        return innerKeyVaultClient.getCertificateVersions(vaultBaseUrl, certificateName);
    }
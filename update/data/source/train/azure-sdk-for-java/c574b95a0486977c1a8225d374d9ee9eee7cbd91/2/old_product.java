public PagedList<CertificateItem> listCertificates(final String vaultBaseUrl)
            throws KeyVaultErrorException, IOException, IllegalArgumentException {
        return innerKeyVaultClient.getCertificates(vaultBaseUrl);
    }
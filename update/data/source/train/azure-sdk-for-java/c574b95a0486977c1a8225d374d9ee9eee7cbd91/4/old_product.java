public PagedList<CertificateItem> listCertificates(final String vaultBaseUrl, final Integer maxresults)
            throws KeyVaultErrorException, IOException, IllegalArgumentException {
        return innerKeyVaultClient.getCertificates(vaultBaseUrl, maxresults);
    }
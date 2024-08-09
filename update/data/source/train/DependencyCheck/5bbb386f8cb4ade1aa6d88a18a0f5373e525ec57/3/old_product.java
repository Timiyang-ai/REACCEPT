protected void determineCPE(Dependency dependency) throws CorruptIndexException, IOException, ParseException {
        String vendors = "";
        String products = "";
        for (Confidence confidence : Confidence.values()) {
            if (dependency.getVendorEvidence().contains(confidence)) {
                vendors = addEvidenceWithoutDuplicateTerms(vendors, dependency.getVendorEvidence(), confidence);
                LOGGER.debug("vendor search: {}", vendors);
            }
            if (dependency.getProductEvidence().contains(confidence)) {
                products = addEvidenceWithoutDuplicateTerms(products, dependency.getProductEvidence(), confidence);
                LOGGER.debug("product search: {}", products);
            }
            if (!vendors.isEmpty() && !products.isEmpty()) {
                final List<IndexEntry> entries = searchCPE(vendors, products, dependency.getVendorEvidence().getWeighting(),
                        dependency.getProductEvidence().getWeighting());
                if (entries == null) {
                    continue;
                }
                boolean identifierAdded = false;
                for (IndexEntry e : entries) {
                    LOGGER.debug("Verifying entry: {}", e);
                    if (verifyEntry(e, dependency)) {
                        final String vendor = e.getVendor();
                        final String product = e.getProduct();
                        LOGGER.debug("identified vendor/product: {}/{}", vendor, product);
                        identifierAdded |= determineIdentifiers(dependency, vendor, product, confidence);
                    }
                }
                if (identifierAdded) {
                    break;
                }
            }
        }
    }
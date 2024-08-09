protected void determineCPE(Dependency dependency) throws CorruptIndexException, IOException, ParseException {
        String vendors = "";
        String products = "";
        for (Confidence confidence : Confidence.values()) {
            if (dependency.contains(EvidenceType.VENDOR, confidence)) {
                vendors = addEvidenceWithoutDuplicateTerms(vendors, dependency.getIterator(EvidenceType.VENDOR, confidence));
                LOGGER.debug("vendor search: {}", vendors);
            }
            if (dependency.contains(EvidenceType.PRODUCT, confidence)) {
                products = addEvidenceWithoutDuplicateTerms(products, dependency.getIterator(EvidenceType.PRODUCT, confidence));
                LOGGER.debug("product search: {}", products);
            }
            if (!vendors.isEmpty() && !products.isEmpty()) {
                final List<IndexEntry> entries = searchCPE(vendors, products, dependency.getVendorWeightings(),
                        dependency.getProductWeightings());
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
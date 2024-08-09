protected void determineCPE(Dependency dependency) throws CorruptIndexException, IOException, ParseException {
        //TODO test dojo-war against this. we shold get dojo-toolkit:dojo-toolkit AND dojo-toolkit:toolkit
        String vendors = "";
        String products = "";
        for (Confidence confidence : Confidence.values()) {
            if (dependency.getVendorEvidence().contains(confidence)) {
                vendors = addEvidenceWithoutDuplicateTerms(vendors, dependency.getVendorEvidence(), confidence);
            }
            if (dependency.getProductEvidence().contains(confidence)) {
                products = addEvidenceWithoutDuplicateTerms(products, dependency.getProductEvidence(), confidence);
            }
            /* bug fix for #40 - version evidence is not showing up as "used" in the reports if there is no
             * CPE identified. As such, we are "using" the evidence and ignoring the results. */
            if (dependency.getVersionEvidence().contains(confidence)) {
                addEvidenceWithoutDuplicateTerms("", dependency.getVersionEvidence(), confidence);
            }
            if (!vendors.isEmpty() && !products.isEmpty()) {
                final List<IndexEntry> entries = searchCPE(vendors, products, dependency.getProductEvidence().getWeighting(),
                        dependency.getVendorEvidence().getWeighting());

                boolean identifierAdded = false;
                for (IndexEntry e : entries) {
                    if (verifyEntry(e, dependency)) {
                        final String vendor = e.getVendor();
                        final String product = e.getProduct();
                        identifierAdded |= determineIdentifiers(dependency, vendor, product);
                    }
                }
                if (identifierAdded) {
                    break;
                }
            }
        }
    }
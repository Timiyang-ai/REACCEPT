protected void determineCPE(Dependency dependency) throws CorruptIndexException, IOException, ParseException {
        Confidence vendorConf = Confidence.HIGH;
        Confidence productConf = Confidence.HIGH;
        Confidence versionConf = Confidence.HIGH;

        String vendors = addEvidenceWithoutDuplicateTerms("", dependency.getVendorEvidence(), vendorConf);
        String products = addEvidenceWithoutDuplicateTerms("", dependency.getProductEvidence(), productConf);
        String versions = addEvidenceWithoutDuplicateTerms("", dependency.getVersionEvidence(), versionConf);

        boolean found = false;
        int ctr = 0;
        do {
            List<Entry> entries = searchCPE(vendors, products, versions, dependency.getProductEvidence().getWeighting(),
                    dependency.getVendorEvidence().getWeighting());


            for (Entry e : entries) {
                if (verifyEntry(e, dependency)) {
                    found = true;

                    dependency.addIdentifier(
                            "cpe",
                            e.getName(),
                            e.getTitle(),
                            "http://web.nvd.nist.gov/view/vuln/search?cpe="
                            + URLEncoder.encode(e.getName(), "UTF-8"));
                }
            }


            if (!found) {
                int round = ctr % 3;
                if (round == 0) {
                    vendorConf = reduceConfidence(vendorConf);
                    if (dependency.getVendorEvidence().contains(vendorConf)) {
                        //vendors += " " + dependency.getVendorEvidence().toString(vendorConf);
                        vendors = addEvidenceWithoutDuplicateTerms(vendors, dependency.getVendorEvidence(), vendorConf);
                    } else {
                        ctr += 1;
                        round += 1;
                    }
                }
                if (round == 1) {
                    productConf = reduceConfidence(productConf);
                    if (dependency.getProductEvidence().contains(productConf)) {
                        //products += " " + dependency.getProductEvidence().toString(productConf);
                        products = addEvidenceWithoutDuplicateTerms(products, dependency.getProductEvidence(), productConf);
                    } else {
                        ctr += 1;
                        round += 1;
                    }
                }
                if (round == 2) {
                    versionConf = reduceConfidence(versionConf);
                    if (dependency.getVersionEvidence().contains(versionConf)) {
                        //versions += " " + dependency.getVersionEvidence().toString(versionConf);
                        versions = addEvidenceWithoutDuplicateTerms(versions, dependency.getVersionEvidence(), versionConf);
                    }
                }
            }
        } while (!found && (++ctr) < 9);
    }
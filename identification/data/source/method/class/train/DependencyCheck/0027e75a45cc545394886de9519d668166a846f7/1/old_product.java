protected void determineCPE(Dependency dependency) throws CorruptIndexException, IOException, ParseException {
        Confidence vendorConf = Confidence.HIGH;
        Confidence productConf = Confidence.HIGH;
        Confidence versionConf = Confidence.HIGH;

        String vendors = addEvidenceWithoutDuplicateTerms("", dependency.getVendorEvidence(), vendorConf);
        //dependency.getVendorEvidence().toString(vendorConf);
//        if ("".equals(vendors)) {
//            vendors = STRING_THAT_WILL_NEVER_BE_IN_THE_INDEX;
//        }
        String products = addEvidenceWithoutDuplicateTerms("", dependency.getProductEvidence(), productConf);
        ///dependency.getProductEvidence().toString(productConf);
//        if ("".equals(products)) {
//            products = STRING_THAT_WILL_NEVER_BE_IN_THE_INDEX;
//        }
        String versions = addEvidenceWithoutDuplicateTerms("", dependency.getVersionEvidence(), versionConf);
        //dependency.getVersionEvidence().toString(versionConf);
//        if ("".equals(versions)) {
//            versions = STRING_THAT_WILL_NEVER_BE_IN_THE_INDEX;
//        }

        boolean found = false;
        int cnt = 0;
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
                int round = cnt % 3;
                if (round == 0) {
                    vendorConf = reduceConfidence(vendorConf);
                    if (dependency.getVendorEvidence().contains(vendorConf)) {
                        //vendors += " " + dependency.getVendorEvidence().toString(vendorConf);
                        vendors = addEvidenceWithoutDuplicateTerms(vendors, dependency.getVendorEvidence(), vendorConf);
                    } else {
                        cnt += 1;
                        round += 1;
                    }
                }
                if (round == 1) {
                    productConf = reduceConfidence(productConf);
                    if (dependency.getProductEvidence().contains(productConf)) {
                        //products += " " + dependency.getProductEvidence().toString(productConf);
                        products = addEvidenceWithoutDuplicateTerms(products, dependency.getProductEvidence(), productConf);
                    } else {
                        cnt += 1;
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
        } while (!found && (++cnt) < 9);
    }
protected void determineCPE(Dependency dependency) throws CorruptIndexException, IOException, ParseException, AnalysisException {
        final Map<String, MutableInt> vendors = new HashMap<>();
        final Map<String, MutableInt> products = new HashMap<>();
        final Set<Integer> previouslyFound = new HashSet<>();

        for (Confidence confidence : Confidence.values()) {
            collectTerms(vendors, dependency.getIterator(EvidenceType.VENDOR, confidence));
            LOGGER.debug("vendor search: {}", vendors);
            collectTerms(products, dependency.getIterator(EvidenceType.PRODUCT, confidence));
            LOGGER.debug("product search: {}", products);
            if (!vendors.isEmpty() && !products.isEmpty()) {
                final List<IndexEntry> entries = searchCPE(vendors, products,
                        dependency.getVendorWeightings(), dependency.getProductWeightings());
                if (entries == null) {
                    continue;
                }

                boolean identifierAdded = false;
                //filtering on score seems to create additional FN - but maybe we should continue to investigate this option
//                StandardDeviation stdev = new StandardDeviation();
//                float maxScore = 0;
//                for (IndexEntry e : entries) {
//                    if (previouslyFound.contains(e.getDocumentId())) {
//                        continue;
//                    }
//                    stdev.increment((double) e.getSearchScore());
//                    if (maxScore < e.getSearchScore()) {
//                        maxScore = e.getSearchScore();
//                    }
//                }
//                double filter = maxScore - (stdev.getResult() * 5);

                for (IndexEntry e : entries) {
                    if (previouslyFound.contains(e.getDocumentId()) /*|| (filter > 0 && e.getSearchScore() < filter)*/) {
                        continue;
                    }
                    previouslyFound.add(e.getDocumentId());
                    //LOGGER.error("\"Verifying entry\",\"{}\",\"{}\",\"{}\",\"{}\",\"{}\",\"{}\"", dependency.getFileName(),
                    //e.getVendor(), e.getProduct(), confidence.toString(), e.getSearchScore(), filter);
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
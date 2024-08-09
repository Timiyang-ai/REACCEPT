protected boolean determineIdentifiers(Dependency dependency, String vendor, String product,
            Confidence currentConfidence) throws UnsupportedEncodingException, AnalysisException {
        final Set<VulnerableSoftware> cpes = cve.getCPEs(vendor, product);
        if (cpes.isEmpty()) {
            return false;
        }
        DependencyVersion bestGuess = new DependencyVersion("-");
        Confidence bestGuessConf = null;
        boolean hasBroadMatch = false;
        final List<IdentifierMatch> collected = new ArrayList<>();

        //TODO the following algorithm incorrectly identifies things as a lower version
        // if there lower confidence evidence when the current (highest) version number
        // is newer then anything in the NVD.
        for (Confidence conf : Confidence.values()) {
            for (Evidence evidence : dependency.getIterator(EvidenceType.VERSION, conf)) {
                final DependencyVersion evVer = DependencyVersionUtil.parseVersion(evidence.getValue());
                if (evVer == null) {
                    continue;
                }
                for (VulnerableSoftware vs : cpes) {
                    final DependencyVersion dbVer;
                    if (vs.getUpdate() != null && !vs.getUpdate().isEmpty()) {
                        dbVer = DependencyVersionUtil.parseVersion(vs.getVersion() + '.' + vs.getUpdate());
                    } else {
                        dbVer = DependencyVersionUtil.parseVersion(vs.getVersion());
                    }
                    if (dbVer == null) { //special case, no version specified - everything is vulnerable
                        hasBroadMatch = true;
                        final String url = String.format(NVD_SEARCH_URL, URLEncoder.encode(vs.getName(), StandardCharsets.UTF_8.name()));
                        final IdentifierMatch match = new IdentifierMatch("cpe", vs.getName(), url, IdentifierConfidence.BROAD_MATCH, conf);
                        collected.add(match);
                    } else if (evVer.equals(dbVer)) { //yeah! exact match
                        final String url = String.format(NVD_SEARCH_URL, URLEncoder.encode(vs.getName(), StandardCharsets.UTF_8.name()));
                        final IdentifierMatch match = new IdentifierMatch("cpe", vs.getName(), url, IdentifierConfidence.EXACT_MATCH, conf);
                        collected.add(match);

                        //TODO the following isn't quite right is it? need to think about this guessing game a bit more.
                    } else if (evVer.getVersionParts().size() <= dbVer.getVersionParts().size()
                            && evVer.matchesAtLeastThreeLevels(dbVer)) {
                        if (bestGuessConf == null || bestGuessConf.compareTo(conf) > 0) {
                            if (bestGuess.getVersionParts().size() < dbVer.getVersionParts().size()) {
                                bestGuess = dbVer;
                                bestGuessConf = conf;
                            }
                        }
                    }
                }
                if ((bestGuessConf == null || bestGuessConf.compareTo(conf) > 0)
                        && bestGuess.getVersionParts().size() < evVer.getVersionParts().size()) {
                    bestGuess = evVer;
                    bestGuessConf = conf;
                }
            }
        }
        final String cpeName = String.format("cpe:/a:%s:%s:%s", vendor, product, bestGuess.toString());
        String url = null;
        if (hasBroadMatch) { //if we have a broad match we can add the URL to the best guess.
            final String cpeUrlName = String.format("cpe:/a:%s:%s", vendor, product);
            url = String.format(NVD_SEARCH_URL, URLEncoder.encode(cpeUrlName, StandardCharsets.UTF_8.name()));
        }
        if (bestGuessConf
                == null) {
            bestGuessConf = Confidence.LOW;
        }
        final IdentifierMatch match = new IdentifierMatch("cpe", cpeName, url, IdentifierConfidence.BEST_GUESS, bestGuessConf);

        collected.add(match);

        Collections.sort(collected);
        final IdentifierConfidence bestIdentifierQuality = collected.get(0).getConfidence();
        final Confidence bestEvidenceQuality = collected.get(0).getEvidenceConfidence();
        boolean identifierAdded = false;
        for (IdentifierMatch m : collected) {
            if (bestIdentifierQuality.equals(m.getConfidence())
                    && bestEvidenceQuality.equals(m.getEvidenceConfidence())) {
                final Identifier i = m.getIdentifier();
                if (bestIdentifierQuality == IdentifierConfidence.BEST_GUESS) {
                    i.setConfidence(Confidence.LOW);
                } else {
                    i.setConfidence(bestEvidenceQuality);
                }
                //TODO - while this gets the job down it is slow; consider refactoring
                dependency.addIdentifier(i);
                suppression.analyze(dependency, null);
                if (dependency.getIdentifiers().contains(i)) {
                    identifierAdded = true;
                }
            }
        }
        return identifierAdded;
    }
private void addCriticalityToVulnerability(String parentName, Vulnerability vulnerability, String nextLine) {
        if (null != vulnerability) {
            final String criticality = nextLine.substring(CRITICALITY.length()).trim();
            float score = -1.0f;
            Vulnerability v = null;
            if (cvedb != null) {
                try {
                    v = cvedb.getVulnerability(vulnerability.getName());
                } catch (DatabaseException ex) {
                    LOGGER.debug("Unable to look up vulnerability {}", vulnerability.getName());
                }
            }
            if (v != null) {
                score = v.getCvssScore();
                vulnerability.setCvssAccessComplexity(v.getCvssAccessComplexity());
                vulnerability.setCvssAccessVector(v.getCvssAccessVector());
                vulnerability.setCvssAuthentication(v.getCvssAuthentication());
                vulnerability.setCvssAvailabilityImpact(v.getCvssAvailabilityImpact());
                vulnerability.setCvssConfidentialityImpact(v.getCvssConfidentialityImpact());
                vulnerability.setCvssIntegrityImpact(v.getCvssIntegrityImpact());
            } else if ("High".equalsIgnoreCase(criticality)) {
                score = 8.5f;
            } else if ("Medium".equalsIgnoreCase(criticality)) {
                score = 5.5f;
            } else if ("Low".equalsIgnoreCase(criticality)) {
                score = 2.0f;
            }
            vulnerability.setCvssScore(score);
        }
        LOGGER.debug("bundle-audit ({}): {}", parentName, nextLine);
    }
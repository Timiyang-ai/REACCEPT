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
            if (v != null && (v.getCvssV2() != null || v.getCvssV3() != null)) {
                if (v.getCvssV2() != null) {
                    vulnerability.setCvssV2(v.getCvssV2());
                }
                if (v.getCvssV3() != null) {
                    vulnerability.setCvssV3(v.getCvssV3());
                }
            } else {
                if ("High".equalsIgnoreCase(criticality)) {
                    score = 8.5f;
                } else if ("Medium".equalsIgnoreCase(criticality)) {
                    score = 5.5f;
                } else if ("Low".equalsIgnoreCase(criticality)) {
                    score = 2.0f;
                }
                vulnerability.setCvssV2(new CvssV2(score, "-", "-", "-", "-", "-", "-", criticality));
            }
        }
        LOGGER.debug("bundle-audit ({}): {}", parentName, nextLine);
    }
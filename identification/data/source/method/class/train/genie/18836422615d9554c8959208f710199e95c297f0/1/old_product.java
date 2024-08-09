public String getS3SiteXmlsAsCsv() {
        logger.debug("called");

        StringBuilder csv = new StringBuilder();
        if (s3CoreSiteXml != null) {
            csv.append(s3CoreSiteXml);
        }
        csv.append(",");

        if (s3MapredSiteXml != null) {
            csv.append(s3MapredSiteXml);
        }
        csv.append(",");

        if (s3HdfsSiteXml != null) {
            csv.append(s3HdfsSiteXml);
        }

        return csv.toString();
    }
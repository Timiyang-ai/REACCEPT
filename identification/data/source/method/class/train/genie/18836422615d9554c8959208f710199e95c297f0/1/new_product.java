public String getS3SiteXmlsAsCsv() {
        logger.debug("called");

        StringBuilder csv = new StringBuilder();
        if (s3CoreSiteXml != null) {
            csv.append(s3CoreSiteXml);
        }
        
        if (s3MapredSiteXml != null) {
        	csv.append(",");
        	csv.append(s3MapredSiteXml);
        }
        
        if (s3HdfsSiteXml != null) {
        	csv.append(",");
        	csv.append(s3HdfsSiteXml);
        }
        
        if (s3YarnSiteXml != null) {
        	csv.append(",");
        	csv.append(s3YarnSiteXml);
        }

        return csv.toString();
    }
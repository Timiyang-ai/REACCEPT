public String getLicense()
    {
        String license = getMetadata("license");

        if (license == null || license.trim().equals(""))
        {
            // Fallback to site-wide default
            license = LicenseManager.getDefaultSubmissionLicense();
        }

        return license;
    }
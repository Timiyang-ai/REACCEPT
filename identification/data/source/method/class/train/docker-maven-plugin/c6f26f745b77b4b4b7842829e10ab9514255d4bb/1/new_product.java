public static String initAndValidate(List<ImageConfiguration> images, String apiVersion, NameFormatter nameFormatter,
                                         Logger log) {
        // Init and validate configs. After this step, getResolvedImages() contains the valid configuration.
        for (ImageConfiguration imageConfiguration : images) {
            apiVersion = EnvUtil.extractLargerVersion(apiVersion, imageConfiguration.initAndValidate(nameFormatter, log));
        }
        return apiVersion;
    }
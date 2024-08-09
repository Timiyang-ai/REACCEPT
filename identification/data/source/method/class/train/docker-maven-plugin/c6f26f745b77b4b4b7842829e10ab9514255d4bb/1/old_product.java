public static String initAndValidate(List<ImageConfiguration> images, String apiVersion,
                                         Logger log) {
        // Init and validate configs. After this step, getResolvedImages() contains the valid configuration.
        return initAndValidateConfiguration(apiVersion, log, images);
    }
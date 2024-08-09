public static SdkGenerator createSdkGenerator(SdkPlatform sdkPlatform) {
        switch (sdkPlatform) {
        case JAVA:
            return new JavaSdkGenerator(sdkPlatform);
        case ANDROID:
            return new JavaSdkGenerator(sdkPlatform);
        default:
            return null;
        }
    }
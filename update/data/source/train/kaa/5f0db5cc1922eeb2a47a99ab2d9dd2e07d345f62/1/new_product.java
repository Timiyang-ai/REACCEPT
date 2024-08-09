public static SdkGenerator createSdkGenerator(SdkPlatform sdkPlatform) {
        switch (sdkPlatform) {
        case JAVA:
        case ANDROID:
            return new JavaSdkGenerator(sdkPlatform);
        case CPP:
            return new CppSdkGenerator();
        case C:
            return new CSdkGenerator();
        case OBJC:
            return new ObjCSdkGenerator();
        default:
            return null;
        }
    }
@Override
    public void initializeClientHelloExtension(EllipticCurvesExtensionMessage extension) {
        byte[] curves = new byte[0];
        for (NamedCurve curve : extension.getSupportedCurvesConfig()) {
            curves = ArrayConverter.concatenate(curves, curve.getValue());
        }

        extension.setExtensionType(ExtensionType.ELLIPTIC_CURVES.getValue());
        extension.setSupportedCurves(curves);
        extension.setSupportedCurvesLength(curves != null ? curves.length : 0);
        extension.setExtensionLength(extension.getSupportedCurvesLength().getValue() + ExtensionByteLength.EXTENSIONS);

        byte[] ecExtensionBytes = ArrayConverter.concatenate(extension.getExtensionType().getValue(), ArrayConverter
                .intToBytes(extension.getExtensionLength().getValue(), ExtensionByteLength.EXTENSIONS), ArrayConverter
                .intToBytes(extension.getSupportedCurvesLength().getValue(), SUPPORTED_ELLIPTIC_CURVES_LENGTH),
                extension.getSupportedCurves().getValue());

        extension.setExtensionBytes(ecExtensionBytes);
    }
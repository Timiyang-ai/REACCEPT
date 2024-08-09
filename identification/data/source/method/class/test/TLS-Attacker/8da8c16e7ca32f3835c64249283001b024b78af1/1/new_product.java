@Override
    public void prepareExtension(TlsContext context) {
        byte[] curves = new byte[0];
        for (NamedCurve curve : context.getConfig().getNamedCurves()) {
            curves = ArrayConverter.concatenate(curves, curve.getValue());
        }
        EllipticCurvesExtensionMessage extension = (EllipticCurvesExtensionMessage) extensionMessage;
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
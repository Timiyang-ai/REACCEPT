public String compressStringifiedRules() {
        final String stringified = stringifyRules();
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        String encodedResult = null;
        try {
            final DeflaterOutputStream dzip = new DeflaterOutputStream(out);
            dzip.write(stringified.getBytes());
            dzip.close();
            encodedResult = Base64.encodeBase64String(out.toByteArray());
        } catch (final IOException e) {
            LOGGER.warn("Exception while compressing security group rules");
        }
        return encodedResult;
    }
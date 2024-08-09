public static CTLogInfo loadLog(InputStream input) throws InvalidLogFileException {
        Scanner scan = new Scanner(input, "UTF-8").useDelimiter("\n");
        // If the scanner can't even read one token then the file must be empty/blank
        if (!scan.hasNext()) {
            return null;
        }

        String description = null, url = null, key = null;
        while (scan.hasNext()) {
            String[] parts = scan.next().split(":", 2);
            if (parts.length < 2) {
                continue;
            }

            String name = parts[0];
            String value = parts[1];
            switch (name) {
                case "description": description = value; break;
                case "url": url = value; break;
                case "key": key = value; break;
            }
        }

        if (description == null || url == null || key == null) {
            throw new InvalidLogFileException("Missing one of 'description', 'url' or 'key'");
        }

        PublicKey pubkey;
        try {
            pubkey = OpenSSLKey.fromPublicKeyPemInputStream(new StringBufferInputStream(
                        "-----BEGIN PUBLIC KEY-----\n" +
                        key + "\n" +
                        "-----END PUBLIC KEY-----")).getPublicKey();
        } catch (InvalidKeyException e) {
            throw new InvalidLogFileException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new InvalidLogFileException(e);
        }

        return new CTLogInfo(pubkey, description, url);
    }
public boolean checkPassword(String password, String encryptedPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String storedPassword = new String(fromHex(encryptedPassword));
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(keyAlgorythm);
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        //This is time independent version of array comparison.
        // This is done to ensure that time based attacks do not happen.
        //Read more here for time based attacks in this context.
        // https://security.stackexchange.com/questions/74547/timing-attack-against-hmac-in-authenticated-encryption
        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
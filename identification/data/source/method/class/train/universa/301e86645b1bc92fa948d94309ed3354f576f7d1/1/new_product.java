private static void exportPublicKeys(Contract contract, String roleName, String fileName, boolean base64) throws IOException {

        if (fileName == null) {
            if (testMode && testRootPath != null) {
                fileName = testRootPath + "Universa_" + roleName + "_public_key";
            } else {
                fileName = "Universa_" + roleName + "_public_key.pub";
            }
        }

        Role role = contract.getRole(roleName);

        if (role != null) {
            Set<PublicKey> keys = role.getKeys();

            int index = 0;
            byte[] data;
            for (PublicKey key : keys) {
                index++;
                data = key.pack();
                String name = fileName.replaceAll("\\.(pub)$", "_key_" + roleName + "_" + index + ".public.unikey");

                if (base64) {
                    name += ".txt";
                }
                try (FileOutputStream fs = new FileOutputStream(name)) {
                    if (base64)
                        fs.write(Base64.encodeLines(data).getBytes());
                    else
                        fs.write(data);
                    fs.close();
                }
            }

            report(roleName + " export public keys ok");
        } else {
            report("export public keys error, role does not exist: " + roleName);
        }
    }
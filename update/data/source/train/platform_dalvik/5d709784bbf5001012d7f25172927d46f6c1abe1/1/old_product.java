@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        // BEGIN android-changed
        if (!(obj instanceof InetAddress)) {
            return false;
        }
        // END android-changed

        // now check if their byte arrays match...
        byte[] objIPaddress = ((InetAddress) obj).ipaddress;
        // BEGIN android-added
        if (objIPaddress.length != ipaddress.length) {
            return false;
        }
        // END android-added
        for (int i = 0; i < objIPaddress.length; i++) {
            if (objIPaddress[i] != this.ipaddress[i]) {
                return false;
            }
        }
        return true;
    }
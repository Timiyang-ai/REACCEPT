public String getHostAddress() {
        return NetworkOs.getnameinfo(this, NI_NUMERICHOST); // Can't throw.
    }
public String getHostAddress() {
        return Libcore.os.getnameinfo(this, NI_NUMERICHOST); // Can't throw.
    }
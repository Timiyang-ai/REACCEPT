public void addBannedAddress(String address) throws InvalidInetAddressBlockException, InvalidInetAddressException {
        if (InetAddressUtils.hasMask(address))
            this.addBannedAddressBlock(InetAddressUtils.parse(address));
        else
            this.addBannedAddress(InetAddressUtils.getAddress(address));
    }
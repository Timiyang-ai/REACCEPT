public void banAddress(String address) throws InvalidInetAddressException {
        if (InetAddressUtils.hasMask(address))
            this.banAddressBlock(InetAddressUtils.parse(address));
        else
            this.banAddress(InetAddressUtils.getAddressForBan(address));
    }
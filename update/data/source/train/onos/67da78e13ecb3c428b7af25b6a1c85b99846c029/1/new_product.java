@Override
    public HostEvent removeIp(HostId hostId, IpAddress ipAddress) {
        hosts.compute(hostId, (id, existingHost) -> {
            if (existingHost != null) {
                checkState(Objects.equals(hostId.mac(), existingHost.mac()),
                        "Existing and new MAC addresses differ.");
                checkState(Objects.equals(hostId.vlanId(), existingHost.vlan()),
                        "Existing and new VLANs differ.");

                Set<IpAddress> addresses = existingHost.ipAddresses();
                if (addresses != null && addresses.contains(ipAddress)) {
                    addresses = new HashSet<>(existingHost.ipAddresses());
                    addresses.remove(ipAddress);
                    removeIpFromHostsByIp(existingHost, ipAddress);
                    return new DefaultHost(existingHost.providerId(),
                            hostId,
                            existingHost.mac(),
                            existingHost.vlan(),
                            existingHost.location(),
                            ImmutableSet.copyOf(addresses),
                            existingHost.annotations());
                } else {
                    return existingHost;
                }
            }
            return null;
        });
        return null;
    }
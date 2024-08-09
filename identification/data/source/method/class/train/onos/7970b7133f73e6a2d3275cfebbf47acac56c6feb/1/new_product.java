public static Set<IpAddress> getSubnetIps(String cidr) {
        SubnetUtils utils = new SubnetUtils(cidr);
        utils.setInclusiveHostCount(false);
        SubnetUtils.SubnetInfo info = utils.getInfo();
        Set<String> allAddresses =
                new HashSet<>(Arrays.asList(info.getAllAddresses()));

        if (allAddresses.size() > 2) {
            allAddresses.remove(info.getLowAddress());
            allAddresses.remove(info.getHighAddress());
        }

        return allAddresses.stream()
                .map(IpAddress::valueOf).collect(Collectors.toSet());
    }
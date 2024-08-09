public NetworkInterfaces networkInterfaces() {
        if (networkInterfaces == null) {
            this.networkInterfaces = new NetworkInterfacesImpl(
                this.networkManagementClient.networkInterfaces(),
                this.networks(),
                this.publicIpAddresses(),
                this.resourceManager.resourceGroups()
            );
        }
        return this.networkInterfaces;
    }
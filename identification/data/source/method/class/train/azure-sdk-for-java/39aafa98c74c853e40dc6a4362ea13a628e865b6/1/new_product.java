public NetworkInterfaces networkInterfaces() {
        if (networkInterfaces == null) {
            this.networkInterfaces = new NetworkInterfacesImpl(
                this.networkManagementClient.networkInterfaces(),
                this.networks(),
                this.publicIpAddresses(),
                this.resourceManager
            );
        }
        return this.networkInterfaces;
    }
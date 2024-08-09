public static String getIntfNameFromPciAddress(Port port) {
        if (port.getProfile() == null || port.getProfile().isEmpty()) {
            log.error("Port profile is not found");
            return null;
        }

        if (!port.getProfile().containsKey(PCISLOT) ||
                Strings.isNullOrEmpty(port.getProfile().get(PCISLOT).toString())) {
            log.error("Failed to retrieve the interface name because of no pci_slot information from the port");
            return null;
        }

        String busNumHex = port.getProfile().get(PCISLOT).toString().split(":")[1];
        String busNumDecimal = String.valueOf(Integer.parseInt(busNumHex, HEX_RADIX));

        String deviceNumHex = port.getProfile().get(PCISLOT).toString()
                .split(":")[2]
                .split("\\.")[0];
        String deviceNumDecimal = String.valueOf(Integer.parseInt(deviceNumHex, HEX_RADIX));

        String functionNumHex = port.getProfile().get(PCISLOT).toString()
                .split(":")[2]
                .split("\\.")[1];
        String functionNumDecimal = String.valueOf(Integer.parseInt(functionNumHex, HEX_RADIX));

        String intfName;

        String vendorInfoForPort = String.valueOf(port.getProfile().get(PCI_VENDOR_INFO));

        if (!portNamePrefixMap().containsKey(vendorInfoForPort)) {
            log.error("Failed to retrieve the interface name because of no port name prefix for vendor ID {}",
                    vendorInfoForPort);
            return null;
        }
        String portNamePrefix = portNamePrefixMap().get(vendorInfoForPort);

        if (functionNumDecimal.equals(ZERO_FUNCTION_NUMBER)) {
            intfName = portNamePrefix + busNumDecimal + PREFIX_DEVICE_NUMBER + deviceNumDecimal;
        } else {
            intfName = portNamePrefix + busNumDecimal + PREFIX_DEVICE_NUMBER + deviceNumDecimal
                    + PREFIX_FUNCTION_NUMBER + functionNumDecimal;
        }

        return intfName;
    }
private void validateNetworkACLItem(final Integer portStart, final Integer portEnd, final List<String> sourceCidrList, final String protocol, final Integer icmpCode, final Integer icmpType,
            final String action, final Integer number) {

        if (portStart != null && !NetUtils.isValidPort(portStart)) {
            throw new InvalidParameterValueException("publicPort is an invalid value: " + portStart);
        }
        if (portEnd != null && !NetUtils.isValidPort(portEnd)) {
            throw new InvalidParameterValueException("Public port range is an invalid value: " + portEnd);
        }

        // start port can't be bigger than end port
        if (portStart != null && portEnd != null && portStart > portEnd) {
            throw new InvalidParameterValueException("Start port can't be bigger than end port");
        }

        // start port and end port must be null for protocol = 'all'
        if ((portStart != null || portEnd != null) && protocol != null && protocol.equalsIgnoreCase("all")) {
            throw new InvalidParameterValueException("start port and end port must be null if protocol = 'all'");
        }

        if (sourceCidrList != null) {
            for (final String cidr : sourceCidrList) {
                if (!NetUtils.isValidIp4Cidr(cidr)) {
                    throw new ServerApiException(ApiErrorCode.PARAM_ERROR, "Source cidrs formatting error " + cidr);
                }
            }
        }

        //Validate Protocol
        if (protocol != null) {
            //Check if protocol is a number
            if (StringUtils.isNumeric(protocol)) {
                final int protoNumber = Integer.parseInt(protocol);
                if (protoNumber < 0 || protoNumber > 255) {
                    throw new InvalidParameterValueException("Invalid protocol number: " + protoNumber);
                }
            } else {
                //Protocol is not number
                //Check for valid protocol strings
                final String supportedProtocols = "tcp,udp,icmp,all";
                if (!supportedProtocols.contains(protocol.toLowerCase())) {
                    throw new InvalidParameterValueException("Invalid protocol: " + protocol);
                }
            }

            // icmp code and icmp type can't be passed in for any other protocol rather than icmp
            if (!protocol.equalsIgnoreCase(NetUtils.ICMP_PROTO) && (icmpCode != null || icmpType != null)) {
                throw new InvalidParameterValueException("Can specify icmpCode and icmpType for ICMP protocol only");
            }

            if (protocol.equalsIgnoreCase(NetUtils.ICMP_PROTO) && (portStart != null || portEnd != null)) {
                throw new InvalidParameterValueException("Can't specify start/end port when protocol is ICMP");
            }
        }

        //validate icmp code and type
        if (icmpType != null) {
            if (icmpType.longValue() != -1 && !NetUtils.validateIcmpType(icmpType.longValue())) {
                throw new InvalidParameterValueException("Invalid icmp type; should belong to [0-255] range");
            }
            if (icmpCode != null) {
                if (icmpCode.longValue() != -1 && !NetUtils.validateIcmpCode(icmpCode.longValue())) {
                    throw new InvalidParameterValueException("Invalid icmp code; should belong to [0-15] range and can"
                            + " be defined when icmpType belongs to [0-40] range");
                }
            }
        }

        //Check ofr valid action Allow/Deny
        if (action != null) {
            if (!("Allow".equalsIgnoreCase(action) || "Deny".equalsIgnoreCase(action))) {
                throw new InvalidParameterValueException("Invalid action. Allowed actions are Allow and Deny");
            }
        }

        //Check for valid number
        if (number != null && number < 1) {
            throw new InvalidParameterValueException("Invalid number. Number cannot be < 1");
        }
    }
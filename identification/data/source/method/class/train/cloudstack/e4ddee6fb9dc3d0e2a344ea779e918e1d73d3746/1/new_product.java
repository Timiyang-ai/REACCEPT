protected void validateIcmpTypeAndCode(NetworkACLItemVO networkACLItemVO) {
        Integer icmpType = networkACLItemVO.getIcmpType();
        Integer icmpCode = networkACLItemVO.getIcmpCode();
        if (icmpType == null) {
            return;
        }
        if (icmpType.longValue() != -1 && !NetUtils.validateIcmpType(icmpType.longValue())) {
            throw new InvalidParameterValueException(String.format("Invalid icmp type [%d]. It should belong to [0-255] range", icmpType));
        }
        if (icmpCode != null) {
            if (icmpCode.longValue() != -1 && !NetUtils.validateIcmpCode(icmpCode.longValue())) {
                throw new InvalidParameterValueException(String.format("Invalid icmp code [%d]. It should belong to [0-16] range and can be defined when icmpType belongs to [0-40] range", icmpCode));
            }
        }
    }
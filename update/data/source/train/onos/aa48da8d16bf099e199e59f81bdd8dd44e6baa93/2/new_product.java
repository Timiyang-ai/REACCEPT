public byte[] getLsaBodyAsByteArray() throws OspfParseException {
        List<Byte> bodyLst = new ArrayList<>();

        try {
            bodyLst.addAll(Bytes.asList(this.networkMask().toOctets()));
            //add each attachedRouters details
            for (Ip4Address attachedRouter : attachedRouters) {
                //attached router
                bodyLst.addAll(Bytes.asList(attachedRouter.toOctets()));
            }
        } catch (Exception e) {
            log.debug("Error::NetworkLSA::getLsrBodyAsByteArray {}", e.getMessage());
            throw new OspfParseException(OspfErrorType.OSPF_MESSAGE_ERROR, OspfErrorType.BAD_MESSAGE);
        }

        return Bytes.toArray(bodyLst);
    }
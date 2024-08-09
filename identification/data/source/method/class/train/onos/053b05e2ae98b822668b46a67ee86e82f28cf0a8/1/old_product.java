public void deleteLsp(IsisMessage lspMessage) {
        LsPdu lsp = (LsPdu) lspMessage;
        String lspKey = lsp.lspId();
        switch (lsp.isisPduType()) {
            case L1LSPDU:
                isisL1Db.remove(lspKey);
                break;
            case L2LSPDU:
                isisL2Db.remove(lspKey);
                break;
            default:
                log.debug("Unknown LSP type to remove..!!!");
                break;
        }
    }
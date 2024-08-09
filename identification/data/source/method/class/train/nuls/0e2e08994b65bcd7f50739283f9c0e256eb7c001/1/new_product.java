@Override
    public void init() {
        String folder = null;
        try {
            NulsConfig.NULS_CONFIG = ConfigLoader.loadIni(NulsConstant.USER_CONFIG_FILE);

            String mode = NulsConfig.NULS_CONFIG.getCfgValue(NulsConstant.CFG_SYSTEM_SECTION, "mode", "main");
            if ("main".equals(mode)) {
                NulsConfig.MODULES_CONFIG = ConfigLoader.loadIni(NulsConstant.MODULES_CONFIG_FILE);
            } else {
                NulsConfig.MODULES_CONFIG = ConfigLoader.loadIni(mode + "/" + NulsConstant.MODULES_CONFIG_FILE);
            }
        } catch (Exception e) {
            Log.error("Client start failed", e);
            throw new RuntimeException("Client start failed");
        }

        TimeService.getInstance().start();

        //set system language
        try {
            NulsConfig.DEFAULT_ENCODING = NulsConfig.NULS_CONFIG.getCfgValue(NulsConstant.CFG_SYSTEM_SECTION, NulsConstant.CFG_SYSTEM_DEFAULT_ENCODING);
            String language = NulsConfig.NULS_CONFIG.getCfgValue(NulsConstant.CFG_SYSTEM_SECTION, NulsConstant.CFG_SYSTEM_LANGUAGE);
            I18nUtils.setLanguage(language);
            String chainId = NulsConfig.NULS_CONFIG.getCfgValue(NulsConstant.CFG_SYSTEM_SECTION, NulsConstant.CFG_SYSTEM_DEFAULT_CHAIN_ID, "8964");
            NulsContext.getInstance().setDefaultChainId(Short.parseShort(chainId));
        } catch (Exception e) {
            Log.error(e);
        }
        SpringLiteContext.init("io.nuls", new ModularServiceMethodInterceptor());
        try {
            NulsConfig.VERSION = getKernelVersion();
            TransactionManager.init();
            ValidatorManager.init();
        } catch (Exception e) {
            Log.error(e);
        }
    }
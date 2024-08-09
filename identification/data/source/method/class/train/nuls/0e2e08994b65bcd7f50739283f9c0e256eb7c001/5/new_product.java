@Override
    public void init() {
        try {
            NulsConfig.NULS_CONFIG = ConfigLoader.loadIni(NulsConstant.USER_CONFIG_FILE);
            NulsConfig.MODULES_CONFIG = ConfigLoader.loadIni(NulsConstant.MODULES_CONFIG_FILE);
        } catch (IOException e) {
            Log.error("Client start failed", e);
            throw new RuntimeException("Client start failed");
        }
        //set system language
        try {
            NulsConfig.DEFAULT_ENCODING = NulsConfig.NULS_CONFIG.getCfgValue(NulsConstant.CFG_SYSTEM_SECTION, NulsConstant.CFG_SYSTEM_DEFAULT_ENCODING);
            String language = NulsConfig.NULS_CONFIG.getCfgValue(NulsConstant.CFG_SYSTEM_SECTION, NulsConstant.CFG_SYSTEM_LANGUAGE);
            I18nUtils.setLanguage(language);
        } catch (Exception e) {
            Log.error(e);
        }
        SpringLiteContext.init("io.nuls", new ModularServiceMethodInterceptor());
        try {
            TransactionManager.init();
            ValidatorManager.init();
            VersionManager.start();
        } catch (Exception e) {
            Log.error(e);
        }
    }
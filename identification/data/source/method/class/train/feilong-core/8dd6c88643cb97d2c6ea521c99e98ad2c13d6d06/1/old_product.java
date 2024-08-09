public static Object toBean(Object json,JsonConfig jsonConfig){
        JSONObject jsonObject = JSONObject.fromObject(json);

        // Ignore missing properties with Json-Lib

        // 避免出现 Unknown property 'orderIdAndCodeMap' on class 'class
        // com.baozun.trade.web.controller.payment.result.command.PaymentResultEntity' 异常
        jsonConfig.setPropertySetStrategy(new PropertyStrategyWrapper(PropertySetStrategy.DEFAULT));
        return JSONObject.toBean(jsonObject, jsonConfig);
    }
public static <T> String toString(ToStringConfig toStringConfig,T...arrays){
        if (Validator.isNullOrEmpty(arrays)){
            return StringUtils.EMPTY;
        }

        //************************************************************************
        ToStringConfig useToStringConfig = null == toStringConfig ? new ToStringConfig() : toStringConfig;
        Object[] operateArray = toObjects(arrays);
        String connector = useToStringConfig.getConnector();
        //************************************************************************

        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = operateArray.length; i < j; ++i){
            @SuppressWarnings("unchecked")
            T t = (T) operateArray[i];

            //如果是null 或者 empty，但是参数值是不拼接，那么跳过,继续循环
            if (Validator.isNullOrEmpty(t) && !useToStringConfig.getIsJoinNullOrEmpty()){
                continue;
            }
            //value转换, 注意:如果 value 是null ,StringBuilder将拼接 "null" 字符串, 详见  java.lang.AbstractStringBuilder#append(String)
            sb.append(null == t ? StringUtils.EMPTY : "" + t); //see StringUtils.defaultString(t)

            if (null != connector){//注意可能传过来的是换行符 不能使用Validator.isNullOrEmpty来判断

                //放心大胆的拼接 connector, 不判断是否是最后一个,最后会截取
                sb.append(connector);
            }
        }

        return StringUtil.substringWithoutLast(sb, connector);
    }
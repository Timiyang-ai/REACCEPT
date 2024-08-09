@Deprecated
    public static <T> String toString(ToStringConfig toStringConfig,T...arrays){
        if (Validator.isNullOrEmpty(arrays)){
            return StringUtils.EMPTY;
        }
        //ConvertUtils.primitiveToWrapper(type)
        ToStringConfig useToStringConfig = null == toStringConfig ? new ToStringConfig() : toStringConfig;

        String connector = useToStringConfig.getConnector();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = arrays.length; i < j; ++i){
            T t = arrays[i];

            //如果是null 或者 empty，但是参数值是不拼接，那么继续循环
            if (Validator.isNullOrEmpty(t) && !useToStringConfig.getIsJoinNullOrEmpty()){
                continue;
            }
            sb.append(t);

            if (null != connector){//注意可能传过来的是 换行符
                sb.append(connector);
            }
        }

        //由于上面的循环中，最后一个元素可能是null或者empty，判断加还是不加拼接符有点麻烦，因此，循环中统一拼接，但是循环之后做截取处理
        String returnValue = sb.toString();

        if (Validator.isNotNullOrEmpty(connector) && returnValue.endsWith(connector)){
            //去掉最后的拼接符
            return StringUtil.substringWithoutLast(returnValue, connector.length());
        }
        return returnValue;
    }
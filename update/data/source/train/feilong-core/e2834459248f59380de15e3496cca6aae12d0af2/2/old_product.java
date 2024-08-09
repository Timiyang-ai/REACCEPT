public static boolean isInterface(Class<?> ownerClass){
        int flag = ownerClass.getModifiers();// 返回此类或接口以整数编码的 Java 语言修饰符
        return Modifier.isInterface(flag);// 对类和成员访问修饰符进行解码
    }
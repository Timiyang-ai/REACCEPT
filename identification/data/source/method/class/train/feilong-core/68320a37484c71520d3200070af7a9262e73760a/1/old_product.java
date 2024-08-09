public static void deleteFileOrDirectory(String fileName){
        File file = new File(fileName);
        if (file.exists()){
            deleteFileOrDirectory(file);
        }
        throw new IllegalArgumentException("file:[" + fileName + "] not exists,please check it!");
    }
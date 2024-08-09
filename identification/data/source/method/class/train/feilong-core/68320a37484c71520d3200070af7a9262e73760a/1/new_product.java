public static boolean deleteFileOrDirectory(String fileName){
        File file = new File(fileName);
        return deleteFileOrDirectory(file);
    }
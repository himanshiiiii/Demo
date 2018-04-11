package enumeration;

public enum Visibility {
    PUBLIC,PRIVATE;

    public static Visibility convert(String str){
        if("PUBLIC"==str.toUpperCase()){
            return Visibility.PUBLIC;
        }
        else
            return Visibility.PRIVATE;
    }
}
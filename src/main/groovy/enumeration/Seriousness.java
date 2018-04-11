package enumeration;

public enum Seriousness{
    SERIOUS,VERYSERIOUS,CASUAL;

    public static Seriousness convert(String str){
        if("VERYSERIOUS"==str.toUpperCase()){
            return Seriousness.VERYSERIOUS;
        }
        else if("SERIOUS"==str.toUpperCase())
            return Seriousness.SERIOUS;
        else
            return Seriousness.CASUAL;
    }
}

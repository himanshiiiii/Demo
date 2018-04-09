package enumeration

enum Seriousness {
    SERIOUS,VERY_SERIOUS,CASUAL

    static Seriousness isSeriousness(String s){
        if (s.equalsIgnoreCase("SERIOUS")){
            return Seriousness.SERIOUS
        }
        else if (s.equalsIgnoreCase("VERY_SERIOUS")){
            return Seriousness.VERY_SERIOUS
        }
        else if (s.equalsIgnoreCase("CASUAL")){
            return Seriousness.CASUAL
        }
        else {
            throw new Exception("String is not of any seriousness")
        }
    }
}
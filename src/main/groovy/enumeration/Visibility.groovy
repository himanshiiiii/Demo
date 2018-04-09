package enumeration

enum Visibility {
    PUBLIC, PRIVATE

    static Visibility isVisibility(String s) {
        if (s.equalsIgnoreCase("PUBLIC")) {
            return PUBLIC
        } else if (s.equalsIgnoreCase("PRIVATE")) {
            return PRIVATE
        } else {
            throw new Exception("Not a valid Visibility")
        }
    }
}



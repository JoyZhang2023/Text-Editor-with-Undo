enum Action {ADD, DELETE};

public record TextOperation(char character, Action actionPerformed) {


    @Override
    public String toString() {
        return "The last action performed was " + actionPerformed + " on character " + character + ".";
    }
}

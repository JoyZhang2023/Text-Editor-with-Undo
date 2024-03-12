import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Stack<TextOperation> texts = new Stack<>();
        addChar('n', texts);
        addChar('o', texts);
        addChar('r', texts);
        addChar('t', texts);

        display(texts); // expecting "nort"

        deleteChar(texts);
        System.out.println();
        System.out.println("After delete action, the word now is ");
        display(texts); // expecting "nor"

        undoAction(texts);
        System.out.println();
        System.out.println("After undo action, the word now is ");
        display(texts);

    }

    public static void addChar(char input, Stack<TextOperation> text) {
        text.push(new TextOperation(input, Action.ADD));
    }

    public static void display(Stack<TextOperation> text) {
        Stack<TextOperation> temp = new Stack<>();
        String output = ""; //text displayed
        while (!text.empty()) {
            if(text.peek().actionPerformed() == Action.DELETE) {
                temp.push(text.pop());
                temp.push(text.pop());
            }else {
                char tempChar = text.pop().character();
                output += tempChar;
                addChar(tempChar, temp);
            }
        }

        for(int i =output.length()-1; i >= 0 ; i--) {
            System.out.print(output.charAt(i));
        }
        // load it back to stack
        while (!temp.empty()) {
            text.push(temp.pop());
        }
    }

    public static void deleteChar(Stack<TextOperation> text) {
        text.push(new TextOperation(text.peek().character(), Action.DELETE));
    }

    public static void undoAction(Stack<TextOperation> text) {
        if(text.peek().actionPerformed() == Action.ADD) {
            // undo add => remove
            deleteChar(text);
        } else if (text.peek().actionPerformed() == Action.DELETE) {
            // undo delete
            text.pop(); // remove delete operation
        }
    }

}
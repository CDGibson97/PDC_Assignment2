package assignment2;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;
/**
 *
 * @author Callum Gibson;
 */
public class OutputTextConversion extends OutputStream {
    
    private JTextArea text;
    
    public OutputTextConversion(JTextArea text){
        this.text = text;
    }
    
    @Override
    public void write(int b) throws IOException{
        text.append(String.valueOf((char)b));
        text.setCaretPosition(text.getDocument().getLength());
    }
    
}

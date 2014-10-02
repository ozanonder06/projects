/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post2.common;
import java.io.*;
/**
 *
 * @author redyoonnk1
 */
public class TransactionWriter {
    //String workingDir = System.getProperty("user.dir");
    //String salesLog = workingDir + "/src/resources/transaction.txt";
    
    private String salesLog;
    
    public TransactionWriter(String _salesLog){
        salesLog = _salesLog;
    }
    
    public void writeContent(String content){
        try {
            File file = new File(salesLog);
            if (!file.exists()) {
                file.createNewFile();
            }
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(content);
            bw.append("\n");
            
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}

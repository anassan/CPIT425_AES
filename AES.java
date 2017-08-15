
/**
 *
 * @author Anas
 */
import java.io.*;
import java.security.Key;
import java.util.concurrent.ThreadLocalRandom;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class AES {

    private String key;
    private InputStream inputFile;
    private OutputStream outputFile;

    public AES() {
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setInputTxt(InputStream inputTxt) {
        this.inputFile = inputTxt;
    }

    public void setOutputTxt(OutputStream outputTxt) {
        this.outputFile = outputTxt;
    }

    public void encrypt() throws Throwable {
        Key s = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, s);
        CipherInputStream cis = new CipherInputStream(inputFile, cipher);
        copyToFile(cis, outputFile);
    }

    public void decrypt() throws Throwable {
        Key s = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, s);
        CipherOutputStream cos = new CipherOutputStream(outputFile, cipher);
        copyToFile(inputFile, cos);
    }

    public void copyToFile(InputStream inputTxt, OutputStream outputTxt) throws IOException {
        byte[] bytes = new byte[16];
        int numBytes;
        while ((numBytes = inputTxt.read(bytes)) != -1) {
            outputTxt.write(bytes, 0, numBytes);
        }
        outputTxt.flush();
        outputTxt.close();
        inputTxt.close();
    }

    public static void main(String[] args) throws IOException, Throwable {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter \ne for Encryption \nd for Decryption \nanything to Exit ");
        String s = in.readLine();

        switch (s) {
            case "e":
            case "E":
                enBrowse();
                generateKey();
                Encrypt();
                break;

            case "d":
            case "D":
                deBrowse();
                deObject.setKey(JOptionPane.showInputDialog("Enter your key : "));
                Decrypt();
                break;

            default:
                System.exit(0);

        }

        System.exit(0);

    }

    public static void enBrowse() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int choice = chooser.showOpenDialog(chooser);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            //saving the extention of the file
            getExention(file);

            enObject = new AES();
            try {
                enObject.setInputTxt(new FileInputStream(file));
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex);
            }
            //display file name 
            System.out.println(file.getAbsolutePath());
        } else {
            JOptionPane.showMessageDialog(null, "File selection cancelled");
        }
    }

    public static void generateKey() {
        //generate a random key 16 Byte
        try {
            K = new byte[16];
        } catch (NumberFormatException e) {
            return;
        }
        for (int i = 0; i < K.length; i++) {
            //random readable ascii code
            K[i] = (byte) ThreadLocalRandom.current().nextInt(33, 127);
        }
        String temp = "";
        for (int i = 0; i < K.length; i++) {
            temp += Character.toString((char) K[i]);
        }

        enObject.setKey(temp);
        System.out.println("Your key is :  " + temp);
    }

    public static void Encrypt() {

        //save the encrypted file
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setSelectedFile(new File("." + extension));
        int choice = chooser.showSaveDialog(chooser);
        if (choice == JFileChooser.APPROVE_OPTION) {
            try {
                //writing the out file to the choosen location
                FileOutputStream output = new FileOutputStream(chooser.getSelectedFile());
                enObject.setOutputTxt(output);
                enObject.encrypt();
                JOptionPane.showMessageDialog(null, chooser.getSelectedFile().getName() + " Have been saved Successfully!");
                output.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex);
            } catch (Throwable ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "File Save cancelled!");
        }

    }

    public static void deBrowse() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int choice = chooser.showOpenDialog(chooser);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            //saving the extention of the file
            getExention(file);
            deObject = new AES();
            try {
                deObject.setInputTxt(new FileInputStream(file));
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex);
            }
            System.out.println(file.getAbsolutePath());
        } else {
            JOptionPane.showMessageDialog(null, "File selection cancelled");
        }
    }

    public static void Decrypt() {

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setSelectedFile(new File("." + extension));
        int choice = chooser.showSaveDialog(chooser);
        if (choice == JFileChooser.APPROVE_OPTION) {
            try {
                //writing the out file to the choosen location
                FileOutputStream output = new FileOutputStream(chooser.getSelectedFile());
                deObject.setOutputTxt(output);
                deObject.decrypt();
                JOptionPane.showMessageDialog(null, chooser.getSelectedFile().getName() + " Have been saved Successfully!");
                output.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex);
            } catch (Throwable ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "File Save cancelled!");
        }

    }

    private static void getExention(File file) {
        int i = file.getAbsolutePath().lastIndexOf('.');
        int p = Math.max(file.getAbsolutePath().lastIndexOf('/'), file.getAbsolutePath().lastIndexOf('\\'));
        if (i > p) {
            extension = file.getAbsolutePath().substring(i + 1);
        }
    }
    public static String extension;
    public static byte[] K;
    public static AES enObject, deObject;
}

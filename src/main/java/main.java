import java.io.*;
import java.nio.ByteBuffer;

public class main {

    static public byte[] arr;
    static public int pos;
    static public StringBuilder str;


    public static String myString(byte[] a) {
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        for (int i = 1; ; i++) {
            b.append(String.format("%02x", a[i - 1]));
            if (i % 64 == 0)
                b.append("\n");
            else
                b.append(" ");
            if (i == (iMax + 1))
                return b.toString();
        }
    }

//    private final static char[] hexArray = "0123456789abcdef".toCharArray();
//
//    public static String bytesToHex(byte[] bytes) {
//        char[] hexChars = new char[bytes.length * 2];
//        for ( int j = 0; j < bytes.length; j++ ) {
//            int v = bytes[j] & 0xFF;
//            hexChars[j * 2] = hexArray[v >>> 4];
//            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
//        }
//        return new String(hexChars);
//    }

    public static void main(String[] args) throws IOException {
        StringBuilder arg = new StringBuilder("src/main/resources/");
        try {
            arg.append(args[0]);
        } catch (RuntimeException e) {
            System.out.println("No file on the command line");
            System.exit(-1);
        }
        try {
            File file = new File(arg.toString());
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                System.out.println("No file on the command line");
                e.printStackTrace();
            }
            arr = new byte[(int) file.length()];
            fis.read(arr);

            ByteBuffer bb = ByteBuffer.wrap(arr, 0, 4);
            if (!String.format("%x", bb.getInt()).equals("ea83f3")) {
                throw new RuntimeException("Not a valid magic header");
            }

            str = new StringBuilder(".name \"");
            for (int i = 4; i < 132; i++) {
                str.append((char) arr[i]);
            }
            str.append("\"\n");
            str.append(".comment \"");
            for (int i = 140; i < 2188; i++) {
                str.append((char) arr[i]);
            }
            str.append("\"\n\n");

            pos = 2192;
            while (pos < arr.length) {
                readCommand();
            }

            String [] out;
            out = args[0].split("\\.");
            StringBuilder output = new StringBuilder(out[0]);
            output.append(".s");
            try (FileWriter writer = new FileWriter(output.toString(), false)) {
                writer.write(str.toString());
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.getMessage();
        }

    }

    private static void readCommand() {
        byte cmd = arr[pos];
        pos++;
        String nameCmd = constant.getNameCmd(cmd);
        str.append(nameCmd).append(" ");
        if (constant.hasByte(cmd)) {
            byte byte_arg = arr[pos];
            pos++;

            for (int i = 0; i < constant.argCount(cmd); i++) {
                int type = (byte_arg >> ((3 - i) * 2)) & 0x3;
                getArg(type, cmd);
                if (i < constant.argCount(cmd) - 1) {
                    str.append(", ");
                }
            }
        } else {
            parseArg(constant.T_DIR, constant.getDirSize(cmd));
        }
        str.append(";\n");
    }

    private static void getArg(int type, byte cmd) {
        switch (type) {
            case constant.T_REG:
                parseArg(type, 1);
                break;
            case constant.T_IND:
                parseArg(type, 2);
                break;
            case constant.T_DIR:
                parseArg(type, constant.getDirSize(cmd));
                break;
        }
    }

    private static void parseArg(int type, int size) {
        switch (type) {
            case constant.T_REG:
                str.append('r');
                break;
            case constant.T_IND:
                break;
            case constant.T_DIR:
                str.append('%');
                break;
        }

        int value = 0;
        for (int i = 0; i < size; i++) {
            value = value << 8;
            value |= arr[pos] & 0x000000ff;
            pos++;
        }
        if (size == 2 && (value & 0x00008000) != 0) {
            value |= 0xffff0000;
        }
        str.append(value);
    }
}


//    mvn exec:java -Dexec.mainClass="main"

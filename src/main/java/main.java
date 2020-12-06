import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {

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



    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/batman.cor");
        FileInputStream fis = new FileInputStream(file);
        byte [] arr = new byte[(int)file.length()];
        fis.read(arr);
        ByteBuffer bb = ByteBuffer.wrap(arr, 0, 4);
        if (!String.format("%x", bb.getInt()).equals("ea83f3")) {
            throw new RuntimeException("Not a valid magic header");
        }

//        System.out.println(String.format("%x", bb.getInt()));

        System.out.println(myString(arr));

//        Path fileLocation = Paths.get("src/main/resources/batman.cor");
//        byte[] data = Files.readAllBytes(fileLocation);
//        List<String> magic_header = new ArrayList();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < data.length; i++) {
//            if (i < 4) {
//                stringBuilder.append(String.format("%x", data[i]));
//            }
//            else if (i >= 4 && i < 132) {
//                if (stringBuilder.toString().equals("0ea83f3")) {
//                    System.out.println("magic header");
//                }
//            }
//        }

//        int magic_header = 0;
//        for (int i = 0; i < 4; i++) {
//            System.out.println("data:" + String.format("%x", data[i]) + " " + data[i]);
//            magic_header = magic_header * 16 + data[i];
//            System.out.println(String.format("%x", magic_header) + " " + magic_header);
//        }


//        System.out.println(myString(data));
        System.out.println(String.format("%x", 0xea83f3));
    }
}

//package test;
//
//import java.io.IOException;
//
//
//public class ProcessBuilderTest {
//
//    public static void main(String arg[]) throws IOException {
//
//        ProcessBuilder pb = new ProcessBuilder(
//                "curl",
//                "-s",
//                "-XPOST 'http://admin:admin@localhost:8080/createItem?name=CopiedJob' ",
//                "--data-binary @mylocaltestconfig.xml ",
//                "-H",
//                "\"Content-Type:text/xml\""
//                );
//
//        pb.start();
//
//
//    }
//}
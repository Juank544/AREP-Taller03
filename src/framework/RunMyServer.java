package framework;

import java.io.IOException;

public class RunMyServer {
    public static void main(String... args) throws IOException {
        ECISpringBoot.get_instance().startServer();
    }
}

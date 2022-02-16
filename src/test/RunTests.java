package test;

import java.lang.reflect.Method;

public class RunTests {
    public static void main(String[] args) throws Exception {
        int passed = 0, failed = 0;
        // Añadir el nombre de la clase Pruebas como Program Arguments
        for (Method m : Class.forName(args[0]).getMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                try {
                    m.invoke(null);
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("test.Test %s failed: %s %n", m, ex.getCause());
                    failed++;
                }}}
        System.out.printf("Passed: %d, Failed %d%n", passed, failed);
    }
}

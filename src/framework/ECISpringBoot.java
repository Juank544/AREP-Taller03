package framework;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ECISpringBoot {

    private Map<String, Method> services = new HashMap<>();

    private static ECISpringBoot _instance = new ECISpringBoot();

    private ECISpringBoot(){}

    public static ECISpringBoot get_instance(){
        return _instance;
    }

    public void startServer() throws IOException {
        loadComponents();
        try {
            HttpServer httpServer = new HttpServer();
            httpServer.start();
        } catch (IOException e) {
            Logger.getLogger(ECISpringBoot.class.getName());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void loadComponents() {
        String[] componentList = searchComponentList();
        for (String componentname : componentList){
            System.out.println("cargando servicio: " + componentname);
            loadServices(componentname);
        }
    }

    private void loadServices(String componentname) {
        try {
            Class c = Class.forName(componentname);
            Method[] declaredMethods = c.getDeclaredMethods();
            for (Method m: declaredMethods){
                if (m.isAnnotationPresent(Service.class)){
                    Service a = m.getAnnotation(Service.class);
                    String serviceName = a.value();
                    System.out.println("Loading service: " + serviceName);
                    services.put(a.value(), m);
                }
            }
        } catch (ClassNotFoundException e) {
            Logger.getLogger(ECISpringBoot.class.getName());
        }
    }

    public String invokeServices(String serviceName) {
        System.out.println("invocando: "+ serviceName);
        Method serviceMethod = services.get(serviceName);
        try {
            return (String) serviceMethod.invoke(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "service error";
    }

    private String[] searchComponentList() {
        return new String[]{"framework.examples.StatusService"};
    }
}

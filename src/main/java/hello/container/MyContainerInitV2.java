package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@HandlesTypes(AppInit.class)
public class MyContainerInitV2 implements ServletContainerInitializer {
    @Override
    public void onStartup(final Set<Class<?>> set, final ServletContext servletContext) throws ServletException {
        System.out.println("MyContainerV2.onStartup");
        System.out.println("set = " + set);
        System.out.println("servletContext = " + servletContext);

        for (final Class<?> appInitClass : set) {
            try {
                final AppInit appInit =(AppInit) appInitClass.getDeclaredConstructor().newInstance();
                appInit.onStartup(servletContext);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

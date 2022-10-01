package springsecurity.security.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends 
AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
    protected Class <?> [] getRootConfigClasses() {
        return new Class[] {
           WebSecurityConfig.class
        };
        //return null;
    }

    @Override
    protected Class < ? > [] getServletConfigClasses() {
        return new Class[] {
            MvcConfiguration.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {
            "/"
        };

}

	
}

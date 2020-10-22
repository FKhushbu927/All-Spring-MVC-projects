package com.spring.khushbu;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		
		//configure rootcontext for registering any kind of bean
		AnnotationConfigWebApplicationContext rootContext=new AnnotationConfigWebApplicationContext();
		rootContext.register(RootConfig.class);
		rootContext.refresh();
		
		/// Manage the lifecycle of the root application context
		servletContext.addListener(new ContextLoaderListener(rootContext));
		 
		//  Configure web mvc for registering mvc related  beans and controllers
		 AnnotationConfigWebApplicationContext mvcRegister=new AnnotationConfigWebApplicationContext();
		 mvcRegister.register(WebMvcConfig.class);
		ServletRegistration.Dynamic registration=servletContext.addServlet("servlet", new DispatcherServlet( mvcRegister));
		 
		 //provide servlet mapping
		 
		 registration.addMapping("/");
		 
		 //load on startup
		 registration.setLoadOnStartup(1);
		
	}

}

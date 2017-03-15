package com.kstrata.apps.hrm.bean.common;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sun.faces.spi.InjectionProvider;
import com.sun.faces.spi.InjectionProviderException;
import com.sun.faces.vendor.WebContainerInjectionProvider;

/**
 * SpringInjectionProvider (custom com.sun.faces.injectionProvider)
 * so that we can use spring beans using @Autowried inside a managedBean marked with @ManagedBean.
 * Here we are not loosing JSF bean's serializability. Also Spring bean is not serialised, its just
 * that spring bean is autowired each time JSF bean is deserialized(Stateless spring bean)
 * 
 * @author rameshb
 *
 */
public class SpringBeanInjectionProvider implements InjectionProvider {
	
	private static final Logger LOG = LoggerFactory.getLogger(SpringBeanInjectionProvider.class);
	
	private static final WebContainerInjectionProvider CONTAINER_INJECTION_PROVIDER = new WebContainerInjectionProvider();

	@Override
	public void inject(Object managedBean) throws InjectionProviderException {
		CONTAINER_INJECTION_PROVIDER.inject(managedBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void invokePostConstruct(Object managedBean) throws InjectionProviderException {
		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		if (null != webApplicationContext) {
			try {
				webApplicationContext.getAutowireCapableBeanFactory().autowireBeanProperties(managedBean, AutowireCapableBeanFactory.AUTOWIRE_AUTODETECT, false);
			} catch (Exception e) {
				LOG.error("Exception in invokePostConstruct() ", e);
			}
		}
		CONTAINER_INJECTION_PROVIDER.invokePostConstruct(managedBean);
	}

	@Override
	public void invokePreDestroy(Object managedBean) throws InjectionProviderException {
		CONTAINER_INJECTION_PROVIDER.invokePreDestroy(managedBean);
	}

}

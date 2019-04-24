package org.superbiz.struts;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StrutsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrutsApplication.class, args);
    }


    @Bean
    public FilterRegistrationBean<SiteMeshFilter> siteMeshFilter() {

        FilterRegistrationBean<SiteMeshFilter> registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new SiteMeshFilter());
        List<String> urls = new ArrayList<String>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        registrationBean.setOrder(1);
        return registrationBean;

    }

    @Bean
    public FilterRegistrationBean<StrutsPrepareAndExecuteFilter> strutsPrepareAndExecuteFilter() {
        FilterRegistrationBean<StrutsPrepareAndExecuteFilter> registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new StrutsPrepareAndExecuteFilter());
        List<String> urls = new ArrayList<String>();

        urls.add("/");
        urls.add("/addUserForm.action");
        urls.add("/addUser.action");
        urls.add("/findUserForm.action");
        urls.add("/findUser.action");
        urls.add("/listAllUsers.action");
        registrationBean.setUrlPatterns(urls);
        registrationBean.setOrder(2);
        return registrationBean;
    }


    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public AddUser addUser(UserService userService){
        return new AddUser(userService);
    }

    @Bean
    public FindUser findUser(UserService userService){
        return new FindUser(userService);
    }
    @Bean
    public ListAllUsers listAllUsers(UserService userService){
        return new ListAllUsers(userService);
    }


}

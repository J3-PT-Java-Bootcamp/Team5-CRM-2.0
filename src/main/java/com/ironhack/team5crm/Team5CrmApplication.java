package com.ironhack.team5crm;

import com.ironhack.team5crm.views.LoginFrame;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

@SpringBootApplication
public class Team5CrmApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Team5CrmApplication.class)
                .headless(false).run(args);

        // SpringApplication.run(Team5CrmApplication.class, args);

        // The Swing application must be placed on the Swing Event Queue
        // https://stackoverflow.com/a/43684747 -> needs more research to know why
        EventQueue.invokeLater(() -> {

            try {
                var ex = ctx.getBean(LoginFrame.class);
                //ex.main();
            } catch (Exception e) {
                System.out.println(e);
            }

        });

    }

}

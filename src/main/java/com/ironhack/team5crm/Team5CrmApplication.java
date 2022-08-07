package com.ironhack.team5crm;

import com.formdev.flatlaf.FlatLightLaf;
import com.ironhack.team5crm.ui.Menu;
import com.ironhack.team5crm.ui.exceptions.AbortedException;
import com.ironhack.team5crm.ui.exceptions.WrongInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

@SpringBootApplication
public class Team5CrmApplication {


    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Team5CrmApplication.class)
                .headless(false).run(args);

//        SpringApplication.run(Team5CrmApplication.class, args);

        // The Swing application must be placed on the Swing Event Queue
        // https://stackoverflow.com/a/43684747 -> needs more research to know why
        EventQueue.invokeLater(() -> {

            try {
                var ex = ctx.getBean(Menu.class);
                ex.main();
            } catch (Exception e) {
                System.out.println(e);
            }

        });

    }

}

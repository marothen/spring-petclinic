package com.maro.clashroyale;

import com.maro.clashroyale.beans.ClanRankingHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.ApplicationScope;

@SpringBootApplication
public class ClashroyaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClashroyaleApplication.class, args);
    }

}

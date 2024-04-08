package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import org.example.listeners.MemberVoiceChannelEventListener;
import org.example.services.MemberVoiceChannelService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Bot {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        ConfigurableApplicationContext context = SpringApplication.run(Bot.class, args);
        MemberVoiceChannelService memberVoiceChannelService = context.getBean(MemberVoiceChannelService.class);

        String discordToken = dotenv.get("DISCORD_TOKEN");
        JDABuilder.createDefault(discordToken)
                .addEventListeners(new MemberVoiceChannelEventListener(memberVoiceChannelService))
                .build();
    }
}
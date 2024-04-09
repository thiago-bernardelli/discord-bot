package org.example.services;

import net.dv8tion.jda.api.JDABuilder;
import org.example.listeners.UserVoiceChannelEventListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JDAService {
    private final UserVoiceChannelEventListener userVoiceChannelEventListener;
    @Value("${discord.token}")
    private String discordToken;

    public JDAService(UserVoiceChannelEventListener userVoiceChannelEventListener) {
        this.userVoiceChannelEventListener = userVoiceChannelEventListener;
    }

    public void run() {
        JDABuilder.createDefault(discordToken)
                .addEventListeners(userVoiceChannelEventListener)
                .build();
    }
}

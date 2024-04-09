package org.example.services;

import net.dv8tion.jda.api.JDABuilder;
import org.example.listeners.MemberVoiceChannelEventListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JDAService {
    @Value("${discord.token}")
    private String discordToken;

    private final MemberVoiceChannelEventListener memberVoiceChannelEventListener;

    public JDAService(MemberVoiceChannelEventListener memberVoiceChannelEventListener) {
        this.memberVoiceChannelEventListener = memberVoiceChannelEventListener;
    }

    public void run() {
        JDABuilder.createDefault(discordToken)
                .addEventListeners(memberVoiceChannelEventListener)
                .build();
    }
}

package org.example.listeners;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.example.services.UserVoiceChannelService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserVoiceChannelEventListener implements EventListener {
    private final UserVoiceChannelService userVoiceChannelService;

    public UserVoiceChannelEventListener(UserVoiceChannelService userVoiceChannelService) {
        this.userVoiceChannelService = userVoiceChannelService;
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof GuildVoiceUpdateEvent voiceEvent) {
            userVoiceChannelService.saveEvent(voiceEvent);
        }
    }
}

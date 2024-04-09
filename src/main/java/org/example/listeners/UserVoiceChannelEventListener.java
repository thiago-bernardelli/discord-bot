package org.example.listeners;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.example.services.GuildService;
import org.example.services.UserService;
import org.example.services.UserVoiceChannelService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserVoiceChannelEventListener implements EventListener {
    private final UserVoiceChannelService userVoiceChannelService;
    private final UserService userService;
    private final GuildService guildService;

    public UserVoiceChannelEventListener(UserVoiceChannelService userVoiceChannelService,
                                         UserService userService,
                                         GuildService guildService) {
        this.userVoiceChannelService = userVoiceChannelService;
        this.userService = userService;
        this.guildService = guildService;
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof GuildVoiceUpdateEvent voiceEvent) {
            var guild = guildService.saveOrUpdateGuild(voiceEvent.getGuild());
            var user = userService.saveOrUpdateUser(voiceEvent.getMember(), guild);
            userVoiceChannelService.saveEvent(voiceEvent, user);
        }
    }
}

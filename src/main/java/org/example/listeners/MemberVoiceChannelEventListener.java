package org.example.listeners;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.example.services.MemberVoiceChannelService;
import org.jetbrains.annotations.NotNull;

public class MemberVoiceChannelEventListener implements EventListener {
    private final MemberVoiceChannelService memberVoiceChannelService;

    public MemberVoiceChannelEventListener(MemberVoiceChannelService memberVoiceChannelService) {
        this.memberVoiceChannelService = memberVoiceChannelService;
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof GuildVoiceUpdateEvent voiceEvent) {
            memberVoiceChannelService.saveEvent(voiceEvent);
        }
    }
}

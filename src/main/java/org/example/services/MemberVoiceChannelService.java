package org.example.services;

import jakarta.transaction.Transactional;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import org.example.entities.MemberVoiceChannelEvent;
import org.example.repositories.MemberVoiceChannelEventRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberVoiceChannelService {

    private final MemberVoiceChannelEventRepository memberVoiceChannelEventRepository;

    public MemberVoiceChannelService(MemberVoiceChannelEventRepository memberVoiceChannelEventRepository) {
        this.memberVoiceChannelEventRepository = memberVoiceChannelEventRepository;
    }

    @Transactional
    public void saveEvent(GuildVoiceUpdateEvent voiceEvent) {
        String userName = voiceEvent.getMember().getUser().getGlobalName();
        AudioChannel oldChannel = voiceEvent.getOldValue();
        AudioChannel newChannel = voiceEvent.getNewValue();

        if (oldChannel != null) {
            handleChannelEvent(userName, "left", oldChannel);
        }
        if (newChannel != null) {
            handleChannelEvent(userName, "join", newChannel);
        }
    }

    private void handleChannelEvent(String userName, String eventName, AudioChannel channel) {
        MemberVoiceChannelEvent mvce = new MemberVoiceChannelEvent();
        mvce.setMember(userName);
        mvce.setEventName(eventName);
        mvce.setChannel(channel.getName());
        memberVoiceChannelEventRepository.save(mvce);
        System.out.println(userName + " " + eventName + " no canal " + channel.getName());
    }

}

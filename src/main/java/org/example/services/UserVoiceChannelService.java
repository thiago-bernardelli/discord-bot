package org.example.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import org.example.entities.UserVoiceChannelEvent;
import org.example.repositories.UserVoiceChannelEventRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserVoiceChannelService {

    private final UserVoiceChannelEventRepository userVoiceChannelEventRepository;

    public UserVoiceChannelService(UserVoiceChannelEventRepository userVoiceChannelEventRepository) {
        this.userVoiceChannelEventRepository = userVoiceChannelEventRepository;
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
        UserVoiceChannelEvent userVoiceChannelEvent = new UserVoiceChannelEvent();
        userVoiceChannelEvent.setMember(userName);
        userVoiceChannelEvent.setEventName(eventName);
        userVoiceChannelEvent.setChannel(channel.getName());
        userVoiceChannelEventRepository.save(userVoiceChannelEvent);
        log.info("member: {}, event: {}, channel: {}", userName, eventName, channel.getName());
    }

}

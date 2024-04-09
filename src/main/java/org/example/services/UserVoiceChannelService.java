package org.example.services;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import org.example.entities.User;
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


    public void saveEvent(GuildVoiceUpdateEvent voiceEvent, User user) {
        AudioChannel oldChannel = voiceEvent.getOldValue();
        AudioChannel newChannel = voiceEvent.getNewValue();

        if (oldChannel != null) {
            handleChannelEvent(user, "left", oldChannel);
        }
        if (newChannel != null) {
            handleChannelEvent(user, "join", newChannel);
        }
    }

    private void handleChannelEvent(User user, String eventName, AudioChannel channel) {
        UserVoiceChannelEvent userVoiceChannelEvent = new UserVoiceChannelEvent();
        userVoiceChannelEvent.setUserId(user.getId());
        userVoiceChannelEvent.setEventName(eventName);
        userVoiceChannelEvent.setChannel(channel.getName());
        userVoiceChannelEventRepository.save(userVoiceChannelEvent);
        log.info("user: {}, event: {}, channel: {}", user.getName(), eventName, channel.getName());
    }

}

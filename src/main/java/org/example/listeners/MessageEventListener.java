package org.example.listeners;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.example.entities.Guild;
import org.example.services.GuildService;
import org.example.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class MessageEventListener implements EventListener {

    private final UserService userService;
    private final GuildService guildService;

    public MessageEventListener(UserService userService, GuildService guildService) {
        this.userService = userService;
        this.guildService = guildService;
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof MessageReceivedEvent messageEvent) {
            var member = messageEvent.getMember();
            if (member != null) {
                Guild guild = guildService.saveOrUpdateGuild(messageEvent.getGuild());
                userService.saveOrUpdateUser(messageEvent.getMember(), guild);
            }
        }
    }
}
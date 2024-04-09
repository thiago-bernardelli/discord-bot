package org.example.services;

import org.example.entities.Guild;
import org.example.repositories.GuildRepository;
import org.springframework.stereotype.Service;

@Service
public class GuildService {

    private final GuildRepository guildRepository;

    public GuildService(GuildRepository guildRepository) {
        this.guildRepository = guildRepository;
    }


    public Guild saveOrUpdateGuild(net.dv8tion.jda.api.entities.Guild discordGuild) {
        Guild guild = guildRepository.findByDiscordId(discordGuild.getId());
        if (guild == null) {
            guild = new Guild();
            guild.setDiscordId(discordGuild.getId());
        }
        guild.setName(discordGuild.getName());
        return guildRepository.save(guild);
    }
}

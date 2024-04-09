package org.example.services;

import org.example.entities.Guild;
import org.example.entities.User;
import org.example.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User saveOrUpdateUser(net.dv8tion.jda.api.entities.Member discordMember, Guild guild) {
        var discordUser = discordMember.getUser();
        User user = userRepository.findByDiscordIdAndGuildId(discordUser.getId(), guild.getId());
        if (user == null) {
            user = new User();
            user.setDiscordId(discordUser.getId());
            user.setGuildId(guild.getId());
        }
        user.setName(discordUser.getGlobalName());
        if (discordMember.getNickname() != null) {
            user.setName(discordMember.getNickname());
        }
        return userRepository.save(user);
    }
}

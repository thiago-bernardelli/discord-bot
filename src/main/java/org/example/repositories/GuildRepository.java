package org.example.repositories;

import org.example.entities.Guild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuildRepository extends JpaRepository<Guild, Long> {
    Guild findByDiscordId(String discordId);
}

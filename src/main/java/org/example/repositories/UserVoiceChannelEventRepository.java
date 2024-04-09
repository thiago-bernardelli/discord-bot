package org.example.repositories;

import org.example.entities.UserVoiceChannelEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVoiceChannelEventRepository extends JpaRepository<UserVoiceChannelEvent, Long> {
}

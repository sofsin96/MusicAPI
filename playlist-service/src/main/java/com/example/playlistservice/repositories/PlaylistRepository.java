package com.example.playlistservice.repositories;

import com.example.playlistservice.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findAllByName(String name);
}

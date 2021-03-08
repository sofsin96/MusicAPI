package com.example.playlistservice.services;

import com.example.playlistservice.dtos.PlaylistDto;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {
    List<PlaylistDto> getAllPlaylists();

    Optional<PlaylistDto> getOne(Long id);

    PlaylistDto createPlaylist(PlaylistDto playlistDto);

    void delete(Long id);

    PlaylistDto replace(Long id, PlaylistDto playlistDto);

    PlaylistDto update(Long id, PlaylistDto playlistDto);

    List<PlaylistDto> search(String name);
}

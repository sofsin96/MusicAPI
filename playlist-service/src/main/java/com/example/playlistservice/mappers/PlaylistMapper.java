package com.example.playlistservice.mappers;

import com.example.playlistservice.dtos.PlaylistDto;
import com.example.playlistservice.models.Playlist;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlaylistMapper {

    public PlaylistMapper() {
    }

    public PlaylistDto mapp(Playlist playlist) {
        return new PlaylistDto(playlist.getId(), playlist.getName(), playlist.getSongs());
    }

    public Playlist mapp(PlaylistDto playlistDto) {
        return new Playlist(playlistDto.getId(), playlistDto.getName(), playlistDto.getSongs());
    }

    public List<PlaylistDto> mapp(List<Playlist> all) {
        return all
                .stream()
                .map(this::mapp)
                .collect(Collectors.toList());
    }

    public Optional<PlaylistDto> mapp(Optional<Playlist> optionalPlaylist) {
        if (optionalPlaylist.isEmpty())
            return Optional.empty();
        return Optional.of(mapp(optionalPlaylist.get()));
    }
}

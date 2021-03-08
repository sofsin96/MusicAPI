package com.example.songservice.mappers;

import com.example.songservice.dtos.SongDto;
import com.example.songservice.models.Song;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SongMapper {

    public SongMapper() {
    }

    public SongDto mapp(Song song) {
        return new SongDto(song.getId(), song.getTitle(), song.getArtist(), song.getAlbum());
    }

    public Song mapp(SongDto songDto) {
        return new Song(songDto.getId(), songDto.getTitle(), songDto.getArtist(), songDto.getAlbum());
    }

    public List<SongDto> mapp(List<Song> all) {
        return all
                .stream()
                .map(this::mapp)
                .collect(Collectors.toList());
    }

    public Optional<SongDto> mapp(Optional<Song> optionalSong) {
        if (optionalSong.isEmpty())
            return Optional.empty();
        return Optional.of(mapp(optionalSong.get()));
    }
}

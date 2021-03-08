package com.example.songservice.services;

import com.example.songservice.dtos.SongDto;
import com.example.songservice.dtos.SongTitle;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<SongDto> getAllSongs();

    Optional<SongDto> getOne(Long id);

    SongDto createSong(SongDto songDto);

    void delete(Long id);

    SongDto replace(Long id, SongDto songDto);

    SongDto update(Long id, SongTitle songTitle);

    List<SongDto> search(String title);
}

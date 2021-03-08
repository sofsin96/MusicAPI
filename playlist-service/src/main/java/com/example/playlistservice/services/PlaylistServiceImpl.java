package com.example.playlistservice.services;

import com.example.playlistservice.dtos.PlaylistDto;
import com.example.playlistservice.mappers.PlaylistMapper;
import com.example.playlistservice.models.Playlist;
import com.example.playlistservice.repositories.PlaylistRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService{

    private final PlaylistMapper playlistMapper;
    private final PlaylistRepository playlistRepository;

    public PlaylistServiceImpl(PlaylistMapper playlistMapper, PlaylistRepository playlistRepository) {
        this.playlistMapper = playlistMapper;
        this.playlistRepository = playlistRepository;
    }

    @Override
    public List<PlaylistDto> getAllPlaylists() {
        return playlistMapper.mapp(playlistRepository.findAll());
    }

    @Override
    public Optional<PlaylistDto> getOne(Long id) {
        return playlistMapper.mapp(playlistRepository.findById(id));
    }

    @Override
    public PlaylistDto createPlaylist(PlaylistDto playlistDto) {
        if (playlistDto.getName().isEmpty())
            throw new RuntimeException();

        return playlistMapper.mapp(playlistRepository.save(playlistMapper.mapp(playlistDto)));
    }

    @Override
    public void delete(Long id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public PlaylistDto replace(Long id, PlaylistDto playlistDto) {
        Optional<Playlist> playlist = playlistRepository.findById(id);
        if(playlist.isPresent())
        {
            Playlist updatedPlaylist = playlist.get();
            updatedPlaylist.setName(playlistDto.getName());
            return playlistMapper.mapp(playlistRepository.save(updatedPlaylist));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id " + id + " not found.");
    }

    @Override
    public PlaylistDto update(Long id, PlaylistDto playlistDto) {
        Optional<Playlist> playlist = playlistRepository.findById(id);
        if(playlist.isPresent()) {
            Playlist updatedPlaylist = playlist.get();
            if(playlistDto.getName() != null)
                updatedPlaylist.setName(playlistDto.getName());
            return playlistMapper.mapp(playlistRepository.save(updatedPlaylist));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id " + id + " not found.");
    }

    @Override
    public List<PlaylistDto> search(String name) {
        return playlistMapper.mapp(playlistRepository.findAllByName(name));
    }
}

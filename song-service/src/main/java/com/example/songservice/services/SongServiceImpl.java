package com.example.songservice.services;

import com.example.songservice.dtos.SongDto;
import com.example.songservice.dtos.SongTitle;
import com.example.songservice.mappers.SongMapper;
import com.example.songservice.models.Song;
import com.example.songservice.repositories.SongRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongMapper songMapper;
    private final SongRepository songRepository;

    public SongServiceImpl(SongMapper songMapper, SongRepository songRepository) {
        this.songMapper = songMapper;
        this.songRepository = songRepository;
    }

    @Override
    public List<SongDto> getAllSongs() {
        return songMapper.mapp(songRepository.findAll());
    }

    @Override
    public Optional<SongDto> getOne(Long id) {
        return songMapper.mapp(songRepository.findById(id));
    }

    @Override
    public SongDto createSong(SongDto songDto) {
        if (songDto.getTitle().isEmpty())
            throw new RuntimeException();

        return songMapper.mapp(songRepository.save(songMapper.mapp(songDto)));
    }

    @Override
    public void delete(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public SongDto replace(Long id, SongDto songDto) {
        Optional<Song> song = songRepository.findById(id);
        if(song.isPresent()) {
            Song updatedSong = song.get();
            updatedSong.setTitle(songDto.getTitle());
            updatedSong.setArtist(songDto.getArtist());
            updatedSong.setAlbum(songDto.getAlbum());

            return songMapper.mapp(songRepository.save(updatedSong));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id " + id + " not found.");
    }

    @Override
    public SongDto update(Long id, SongTitle songTitle) {
        Optional<Song> song = songRepository.findById(id);
        if(song.isPresent()) {
            Song updatedSong = song.get();
            if(songTitle.title != null)
                updatedSong.setTitle(songTitle.title);
            return songMapper.mapp(songRepository.save(updatedSong));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id " + id + " not found.");
    }

    public List<SongDto> search(String title) {
        return songMapper.mapp(songRepository.findAllByTitle(title));
    }
}

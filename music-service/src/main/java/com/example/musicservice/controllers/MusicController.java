package com.example.musicservice.controllers;

import com.example.musicservice.models.Playlist;
import com.example.musicservice.models.Song;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {
    private final RestTemplate restTemplate;

    public MusicController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/playlists")
    public List<Playlist> getPlaylists() {
        Playlist[] playlists = restTemplate.getForObject("http://playlist-service/playlists", Playlist[].class);
        return Arrays.asList(playlists);
    }

    @GetMapping("/playlists/{id}")
    public Playlist getOnePlaylist(@PathVariable Long id) {
        return restTemplate.getForObject("http://playlist-service/playlists/" + id, Playlist.class);
    }

    @GetMapping("/songs")
    public List<Song> getSongs() {
        Song[] songs = restTemplate.getForObject("http://song-service/songs", Song[].class);
        return Arrays.asList(songs);
    }
    @GetMapping("/songs/{id}")
    public Song getOneSong(@PathVariable Long id) {
        return restTemplate.getForObject("http://song-service/songs/" + id, Song.class);
    }
}

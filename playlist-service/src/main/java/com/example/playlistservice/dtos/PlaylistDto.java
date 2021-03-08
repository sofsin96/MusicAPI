package com.example.playlistservice.dtos;

import com.example.playlistservice.models.Song;

import java.util.Set;

public class PlaylistDto {
    private long id;
    private String name;
    private Set<Song> songs;

    public PlaylistDto(long id, String name, Set<Song> songs) {
        this.id = id;
        this.name = name;
        this.songs = songs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "PlaylistDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", songs=" + songs +
                '}';
    }
}

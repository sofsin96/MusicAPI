package com.example.playlistservice.controllers;

import com.example.playlistservice.dtos.PlaylistDto;
import com.example.playlistservice.services.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping()
    public List<PlaylistDto> all() {
        return playlistService.getAllPlaylists();
    }

    @GetMapping("/{id}")
    public PlaylistDto one(@PathVariable Long id) {
        return playlistService.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Id " + id + " not found."));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PlaylistDto create(@RequestBody PlaylistDto playlistDto) {
        return playlistService.createPlaylist(playlistDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        playlistService.delete(id);
    }

    @PutMapping("/{id}")
    public PlaylistDto replace(@RequestBody PlaylistDto playlistDto, @PathVariable Long id) {
        return playlistService.replace(id, playlistDto);
    }

    @PatchMapping("/{id}")
    public PlaylistDto update(@RequestBody PlaylistDto playlistDto, @PathVariable Long id) {
        return playlistService.update(id, playlistDto);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<PlaylistDto> search(@RequestParam("name") String name) {
        return playlistService.search(name);
    }
}

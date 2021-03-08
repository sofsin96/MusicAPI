package com.example.songservice.controllers;

import com.example.songservice.dtos.SongDto;
import com.example.songservice.dtos.SongTitle;
import com.example.songservice.services.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping()
    public List<SongDto> all() {
        return songService.getAllSongs();
    }

    @GetMapping("/{id}")
    public SongDto one(@PathVariable Long id) {
        return songService.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Id " + id + " not found."));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public SongDto create(@RequestBody SongDto songDto) {
        return songService.createSong(songDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        songService.delete(id);
    }

    @PutMapping("/{id}")
    public SongDto replace(@RequestBody SongDto songDto, @PathVariable Long id) {
        return songService.replace(id, songDto);
    }

    @PatchMapping("/{id}")
    public SongDto update(@RequestBody SongTitle songTitle, @PathVariable Long id) {
        return songService.update(id, songTitle);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<SongDto> search(@RequestParam("title") String title) {
        return songService.search(title);
    }
}

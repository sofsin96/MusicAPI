package com.example.songservice.controller;

import com.example.songservice.controllers.SongController;
import com.example.songservice.dtos.SongDto;
import com.example.songservice.dtos.SongTitle;
import com.example.songservice.services.SongService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SongController.class)
public class MvcTest {

    @MockBean
    SongService songService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllSongsShouldReturnSongsAsJson() throws Exception {
        when(songService.getAllSongs()).thenReturn(List.of(new SongDto(1L, "Test", "Test", "Test")));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/songs")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void getOneSongShouldReturnSongAsJson() throws Exception {
        when(songService.getOne(1L)).thenReturn(Optional.of(new SongDto(1L, "Test", "Test", "Test")));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/songs/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void getOneSongWithNonExistingIdShouldReturnNotFoundException() throws Exception {
        when(songService.getOne(1L)).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/songs/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void callingPOSTWithNewSongShouldSaveSongToServiceAndReturnCreated() throws Exception {
        SongDto songDto = new SongDto(1L, "Test", "Test", "Test");

        when(songService.createSong(eq(songDto))).thenReturn(new SongDto(1L,"Test","Test", "Test"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/songs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(songDto))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void callingDeleteShouldReturnOK() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/songs/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void callingPUTWithNewSongShouldSaveSongToServiceAndReturnOK() throws Exception {
        SongDto songDto = new SongDto(1L, "Test", "Test", "Test");

        when(songService.replace(anyLong(), any(SongDto.class))).thenReturn(songDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/songs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(songDto))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void callingPATCHWithNewSongTitleShouldSaveChangesToServiceAndReturnOK() throws Exception {
        SongDto songDto = new SongDto(1L, "Test", "Test", "Test");

        when(songService.update(eq(songDto.getId()), any(SongTitle.class))).thenReturn(songDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/songs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(songDto))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }
}

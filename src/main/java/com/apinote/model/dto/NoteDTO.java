package com.apinote.model.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NoteDTO(String title, String description, Long blockId) {

}

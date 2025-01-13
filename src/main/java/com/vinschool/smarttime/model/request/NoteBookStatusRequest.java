package com.vinschool.smarttime.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteBookStatusRequest {
    private String id;
    private boolean isTrain;
}

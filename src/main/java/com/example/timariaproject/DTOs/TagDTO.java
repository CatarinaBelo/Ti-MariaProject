package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.Anunciotag;
import com.example.timariaproject.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TagDTO {
    private Integer id;
    private String nometag;

    public Tag toEntity(){
        Tag tag = new Tag();
        tag.setId(this.id);
        tag.setNometag(this.nometag);
        return tag;
    }
    public Tag toIdEntity(){
        Tag tag = new Tag();
        tag.setId(this.id);
        return tag;
    }
}

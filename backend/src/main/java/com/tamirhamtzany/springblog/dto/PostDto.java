package com.tamirhamtzany.springblog.dto;

import com.tamirhamtzany.springblog.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;
    private String content;
    private String title;
    private String username;

}

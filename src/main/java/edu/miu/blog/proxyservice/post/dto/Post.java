package edu.miu.blog.proxyservice.post.dto;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id","content","user_id"})
public class Post {

    private Long id;
    private String content;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("post_date")
    private Date postDate;

}

package edu.miu.blog.proxyservice.post.dto;


import com.fasterxml.jackson.annotation.*;
import edu.miu.blog.proxyservice.comment.dto.Comment;
import lombok.*;

import java.util.Date;
import java.util.List;

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
    private List<Comment> commentList;
}

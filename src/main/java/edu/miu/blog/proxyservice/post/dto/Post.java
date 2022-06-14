package edu.miu.blog.proxyservice.post.dto;


import com.fasterxml.jackson.annotation.*;
import edu.miu.blog.proxyservice.comment.dto.Comment;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id","content","user_id"})
public class Post {

    private Long id;
    @NotBlank(message = "Post content cannot be null")
    private String content;
    @JsonProperty("user_id")
    @NotNull(message = "USER ID cannot be null")
    private Long userId;
    @JsonProperty("post_date")
    private Date postDate;
    private List<Comment> commentList;
}

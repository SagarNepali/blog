package edu.miu.blog.proxyservice.comment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
/* DTO for comment on blogpost */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"id","message","date","userId","postId"})
public class Comment {

    private Long id;
    @NotNull(message = "Comment message should not be empty")
    private String message;
    @NotNull(message = "USER ID cannot be null")
    private Long userId;
    //@JsonProperty("user_id")
    private Long postId;
    @JsonProperty("date")
    private Date date;

}

package edu.miu.blog.proxyservice.comment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"id","message","date","userId","postId"})
public class Comment {

    private Long id;
    private String message;
    //@JsonProperty("user_id")
    private Long userId;
    //@JsonProperty("user_id")
    private Long postId;
    @JsonProperty("date")
    private Date date;

}

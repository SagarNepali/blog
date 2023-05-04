package edu.miu.blog.exception.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/* Generic API Error response model */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiError {

    private String message;
    private String statusCode;
    private String description;
}

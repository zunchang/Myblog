package com.springbootmyblog.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogAndTag {
    private Long tagId;
    private Long blogId;
}

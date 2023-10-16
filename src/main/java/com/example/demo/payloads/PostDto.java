package com.example.demo.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.entities.Categories;
import com.example.demo.entities.Comment;
import com.example.demo.entities.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {
 @Getter @Setter private String title;
private String content;
 private String imagename;
private Date addedDate;

private CategoryDto category;

private UserDto user;




//private Categories category;
//private User user;

private Set<Comment> comments = new HashSet<>();
}

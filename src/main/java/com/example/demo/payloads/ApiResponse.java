package com.example.demo.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Data

@AllArgsConstructor
public class ApiResponse {
	
	
@Getter	@NonNull private String message;
	@NonNull private boolean success;

}



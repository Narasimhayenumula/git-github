package com.myproject.Firstproject.Exception;

import com.myproject.Firstproject.DTOEntity.UserDTO;

public class CustomerErrorType extends UserDTO {
		private String ErrorMessage;

		public CustomerErrorType(final String errorMessage) {
			super();
			ErrorMessage = errorMessage;
		}

		public String getErrorMessage() {
			return ErrorMessage;
		}
		
			
}

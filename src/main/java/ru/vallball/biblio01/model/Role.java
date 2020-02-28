package ru.vallball.biblio01.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ROLE_READER, ROLE_LIBRARIAN;

	@Override
	public String getAuthority() {
		return name();
	}

}

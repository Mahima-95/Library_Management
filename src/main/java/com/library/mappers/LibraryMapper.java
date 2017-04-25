package com.library.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.library.entity.Library;

@Component
public class LibraryMapper {

	public List<Library> mapLibrary(List<Library> libraries) {
		if (!StringUtils.isEmpty(libraries) && libraries.isEmpty()) {
			return null;
		}
		List<Library> libraryResponse = new ArrayList<Library>(libraries.size());
		for (Library library : libraries) {
			libraryResponse.add(mapLibrary(library));
		}
		return libraryResponse;

	}

	public Library mapLibrary(Library library) {

		Library libraryResponse = new Library();
		BeanUtils.copyProperties(library, libraryResponse);

		return libraryResponse;

	}
}
